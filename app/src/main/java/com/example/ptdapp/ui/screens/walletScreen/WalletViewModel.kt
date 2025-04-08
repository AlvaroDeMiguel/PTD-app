package com.example.ptdapp.ui.screens.walletScreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ptdapp.data.repositories.WalletRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WalletViewModel(
    private val walletRepository: WalletRepository = WalletRepository(),
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) : ViewModel() {

    private val _saldo = MutableStateFlow<Double?>(null)
    val saldo: StateFlow<Double?> = _saldo

    private val _recargaExitosa = MutableStateFlow<Boolean?>(null)
    val recargaExitosa: StateFlow<Boolean?> = _recargaExitosa

    fun cargarSaldo() {
        val userId = auth.currentUser?.uid ?: return
        walletRepository.obtenerSaldoActual(userId) { saldoActual ->
            _saldo.value = saldoActual
        }
    }

    fun recargarSaldo(amount: Double) {
        val userId = auth.currentUser?.uid ?: return

        walletRepository.recargarSaldo(userId, amount) { success ->
            _recargaExitosa.value = success
            if (success) {
                walletRepository.registrarTransaccion(userId, amount)
                cargarSaldo() // Actualiza el saldo después de recargar
            }
        }
    }

    fun resetearRecarga() {
        _recargaExitosa.value = null
    }
}
