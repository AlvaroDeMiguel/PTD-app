package com.example.ptdapp.ui.screens.walletScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ptdapp.data.repositories.WalletRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WalletViewModel(
    private val walletRepository: WalletRepository = WalletRepository()
) : ViewModel() {

    private val _saldo = MutableStateFlow<Double?>(null)
    val saldo: StateFlow<Double?> = _saldo

    private val _recargaExitosa = MutableStateFlow<Boolean?>(null)
    val recargaExitosa: StateFlow<Boolean?> = _recargaExitosa

    private var _pendingAmount: Double? = null

    init {
        observarSaldo()
    }

    private fun observarSaldo() {
        viewModelScope.launch {
            walletRepository.getSaldoFlow().collect {
                _saldo.value = it
            }
        }
    }

    fun setPendingAmount(amount: Double) {
        _pendingAmount = amount
    }

    fun confirmarPagoExitoso() {
        Log.d("WalletViewModel", "✅ confirmando pago exitoso con $_pendingAmount")
        _pendingAmount?.let {
            recargarSaldo(it)
            _pendingAmount = null
        } ?: Log.e("WalletViewModel", "❌ No hay monto pendiente para confirmar")
    }


    private fun recargarSaldo(amount: Double) {
        viewModelScope.launch {
            Log.d("WalletViewModel", "🔄 recargando saldo: $amount")
            val success = walletRepository.recargarSaldo(amount)
            _recargaExitosa.value = success
            if (success) {
                Log.d("WalletViewModel", "✅ Recarga exitosa")

                // FORZAR LECTURA MANUAL
                walletRepository.obtenerSaldoActual { newBalance ->
                    viewModelScope.launch {
                        _saldo.value = newBalance
                    }
                }
            } else {
                Log.e("WalletViewModel", "❌ Error al recargar")
            }
        }
    }



    fun resetearRecarga() {
        _recargaExitosa.value = null
    }
}
