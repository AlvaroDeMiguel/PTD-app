package com.example.ptdapp.ui.screens.detailPTDScreen.saldos


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.ptdapp.data.model.Gasto
import com.example.ptdapp.data.repositories.SaldoRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class SaldoViewModel : ViewModel() {

    private val repository = SaldoRepository()
    private val auth = FirebaseAuth.getInstance()

    private val _saldos = MutableStateFlow<Map<String, Double>>(emptyMap())
    val saldos: StateFlow<Map<String, Double>> = _saldos

    private val _nombreUsuarios = MutableStateFlow<Map<String, String>>(emptyMap())
    val nombreUsuarios: StateFlow<Map<String, String>> = _nombreUsuarios

    private val currentUserUid = auth.currentUser?.uid ?: ""

    private val _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded

    fun calcularSaldo(grupoId: String) {
        viewModelScope.launch {
            val gastos = repository.obtenerGastos(grupoId)
            val saldoPorUsuario = repository.calcularSaldos(gastos)
            _saldos.value = saldoPorUsuario
            _isDataLoaded.value = true // ✅ señal de que ya terminó la carga
        }
    }


    fun obtenerNombresUsuarios(miembros: List<String>) {
        viewModelScope.launch {
            val nombresMap = repository.obtenerNombresUsuarios(miembros)
            _nombreUsuarios.value = nombresMap
        }
    }

    fun obtenerNombreActual(): String {
        return _nombreUsuarios.value[currentUserUid] ?: "Tú"
    }

    fun obtenerSaldoActual(): Double {
        return _saldos.value[currentUserUid] ?: 0.0
    }
}




