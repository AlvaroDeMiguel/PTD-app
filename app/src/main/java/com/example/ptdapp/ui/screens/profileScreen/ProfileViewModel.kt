package com.example.ptdapp.ui.screens.profileScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ptdapp.data.repositories.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class PerfilUsuario(
    val uid: String = "",
    val email: String = "",
    val nombre: String = "",
    val pais: String = "",
    val ciudad: String = ""
)

class ProfileViewModel(
    private val repository: ProfileRepository = ProfileRepository()
) : ViewModel() {

    private val _user = MutableStateFlow<PerfilUsuario?>(null)
    val user: StateFlow<PerfilUsuario?> = _user

    private val _mensajeGuardado = MutableStateFlow(false)
    val mensajeGuardado: StateFlow<Boolean> = _mensajeGuardado

    init {
        cargarDatosUsuario()
    }

    fun cargarDatosUsuario() {
        viewModelScope.launch {
            val perfil = repository.obtenerPerfil()
            _user.value = perfil
        }
    }

    fun actualizarPerfil(nombre: String, pais: String, ciudad: String) {
        viewModelScope.launch {
            val actualizado = repository.actualizarPerfil(nombre, pais, ciudad)
            if (actualizado) {
                _user.value = _user.value?.copy(nombre = nombre, pais = pais, ciudad = ciudad)
                _mensajeGuardado.value = true
            }
        }
    }

    fun resetMensajeGuardado() {
        _mensajeGuardado.value = false
    }
}
