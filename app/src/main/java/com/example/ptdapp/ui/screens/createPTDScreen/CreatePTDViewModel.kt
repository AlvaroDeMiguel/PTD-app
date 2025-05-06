package com.example.ptdapp.ui.screens.createPTDScreen

import androidx.lifecycle.ViewModel
import com.example.ptdapp.R
import com.example.ptdapp.data.model.PTDGroup
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore

class CreatePTDViewModel : ViewModel() {

    private val firestore = Firebase.firestore
    private val auth = Firebase.auth

    fun crearPTD(
        nombre: String,
        descripcion: String,
        iconoId: Int,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val adminId = auth.currentUser?.uid ?: return

        // Mapear el ID del drawable a su nombre
        val iconoNombre = when (iconoId) {
            R.drawable.image -> "image"
            R.drawable.restaurant -> "restaurant"
            R.drawable.credit_card -> "credit_card"
            else -> "image"
        }

        val grupoId = firestore.collection("grupos").document().id

        val nuevoGrupo = PTDGroup(
            id = grupoId,
            adminId = adminId,
            nombre = nombre,
            descripcion = descripcion,
            iconoNombre = iconoNombre, // ✅ ahora se guarda el nombre, no el ID
            miembros = mutableListOf(adminId),
            gastos = mutableListOf()
        )

        firestore.collection("grupos").document(grupoId).set(nuevoGrupo)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onFailure(e) }
    }
}

