package com.example.ptdapp.ui.liquidacionViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ptdapp.data.model.PTDGroup
import com.example.ptdapp.data.repositories.LiquidacionRepository
import com.example.ptdapp.data.repositories.NotificationsRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

sealed class LiquidacionEstado {
    object Idle : LiquidacionEstado()
    object Loading : LiquidacionEstado()
    object Success : LiquidacionEstado()
    data class Error(val mensaje: String) : LiquidacionEstado()
}

class LiquidacionViewModel(
    private val repository: LiquidacionRepository = LiquidacionRepository(),
    private val notificationsRepository: NotificationsRepository = NotificationsRepository()
) : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _estadoLiquidacion = MutableStateFlow<LiquidacionEstado>(LiquidacionEstado.Idle)
    val estadoLiquidacion: StateFlow<LiquidacionEstado> = _estadoLiquidacion

    fun liquidarGrupo(grupoId: String) {
        viewModelScope.launch {
            _estadoLiquidacion.value = LiquidacionEstado.Loading

            val resultado = repository.liquidarGrupo(grupoId)

            if (resultado.isSuccess) {
                // Obtener datos del grupo para notificar
                val grupoSnapshot = db.collection("grupos").document(grupoId).get().await()
                val grupo = grupoSnapshot.toObject(PTDGroup::class.java)

                if (grupo != null) {
                    notificationsRepository.notificarIngresoAGrupo(
                        grupo.nombre,
                        grupo.miembros
                    )
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

