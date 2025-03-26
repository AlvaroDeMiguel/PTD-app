package com.example.ptdapp.ui.screens.profileScreen

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



data class PerfilUsuario(
    val uid: String = "",
    val email: String = "",
    val nombre: String = "",
    val pais: String = "",
    val ciudad: String = ""
)

class ProfileViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private val _user = MutableStateFlow<PerfilUsuario?>(null)
    val user: StateFlow<PerfilUsuario?> = _user

    // 🔔 Estado para mostrar snackbar
    private val _mensajeGuardado = MutableStateFlow(false)
    val mensajeGuardado: StateFlow<Boolean> = _mensajeGuardado

    init {
        cargarDatosUsuario()
    }

    fun cargarDatosUsuario() {
        val uid = auth.currentUser?.uid ?: return
        db.collection("users").document(uid).get()
            .addOnSuccessListener { doc ->
                val user = doc.toObject(PerfilUsuario::class.java)
                _user.value = user
            }
    }

    fun actualizarPerfil(nombre: String, pais: String, ciudad: String) {
        val uid = auth.currentUser?.uid ?: return
        val updates = mapOf(
            "nombre" to nombre,
            "pais" to pais,
            "ciudad" to ciudad
        )
        db.collection("users").document(uid).update(updates)
            .addOnSuccessListener {
                _user.value = _user.value?.copy(nombre = nombre, pais = pais, ciudad = ciudad)
                _mensajeGuardado.value = true // ✅ Activa mensaje
            }
    }

    fun resetMensajeGuardado() {
        _mensajeGuardado.value = false
    }
}

