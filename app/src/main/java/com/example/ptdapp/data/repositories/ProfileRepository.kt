package com.example.ptdapp.data.repositories

import com.example.ptdapp.ui.screens.profileScreen.PerfilUsuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProfileRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    suspend fun obtenerPerfil(): PerfilUsuario? {
        val uid = auth.currentUser?.uid ?: return null
        val doc = db.collection("users").document(uid).get().await()
        return doc.toObject(PerfilUsuario::class.java)
    }

    suspend fun actualizarPerfil(nombre: String, pais: String, ciudad: String): Boolean {
        val uid = auth.currentUser?.uid ?: return false
        val updates = mapOf(
            "nombre" to nombre,
            "pais" to pais,
            "ciudad" to ciudad
        )
        db.collection("users").document(uid).update(updates).await()
        return true
    }
}
