package com.example.ptdapp.ui.liquidacionViewModel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ptdapp.data.model.PTDGroup
import com.example.ptdapp.data.repositories.LiquidacionRepository
import com.example.ptdapp.data.repositories.NotificationsRepository
import com.example.ptdapp.data.repositories.SaldoRepository
import com.example.ptdapp.data.repositories.WalletRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

sealed class LiquidacionEstado {
    object Idle : LiquidacionEstado()
    object Loading : LiquidacionEstado()
    object Success : LiquidacionEstado()
    data class Error(val mensaje: String) : LiquidacionEstado()
}

class LiquidacionViewModel(
    private val repository: LiquidacionRepository = LiquidacionRepository(),
    private val notificationsRepository: NotificationsRepository = NotificationsRepository(),
    private val walletRepository: WalletRepository = WalletRepository(),      // ← nuevo
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()              // ← para obtener UID
) : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _estadoLiquidacion = MutableStateFlow<LiquidacionEstado>(LiquidacionEstado.Idle)
    val estadoLiquidacion: StateFlow<LiquidacionEstado> = _estadoLiquidacion

    fun liquidarGrupo(grupoId: String) {
        viewModelScope.launch {
            _estadoLiquidacion.value = LiquidacionEstado.Loading

            val resultado = repository.liquidarGrupo(grupoId)

            if (resultado.isSuccess) {
                val uid = auth.currentUser?.uid ?: return@launch

                // Calcula cuánto te debían
                val gastos = SaldoRepository().obtenerTodosLosGastosDelUsuario(uid)
                val (_, teDeben) = SaldoRepository().calcularDebesTeDeben(uid, gastos)

                // Aplica la recarga al wallet (solo amount)
                val success = walletRepository.recargarSaldo(teDeben)
                if (!success) {
                    Log.e("LiquidacionVM", "Falló la recarga de saldo tras liquidar grupo")
                }

                // 2) Notifica al grupo
                val grupo = db.collection("grupos")
                    .document(grupoId)
                    .get()
                    .await()
                    .toObject(PTDGroup::class.java)

                grupo?.let {
                    notificationsRepository.notificarIngresoAGrupo(it.nombre, it.miembros)
                }
            }

            _estadoLiquidacion.value = resultado.fold(
                onSuccess = { LiquidacionEstado.Success },
                onFailure = { e -> LiquidacionEstado.Error(e.message ?: "Error desconocido") }
            )
        }
    }

    fun resetEstado() {
        _estadoLiquidacion.value = LiquidacionEstado.Idle
    }
}


