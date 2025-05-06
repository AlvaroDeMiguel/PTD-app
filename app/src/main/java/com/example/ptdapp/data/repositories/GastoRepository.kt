package com.example.ptdapp.data.repositories

import com.example.ptdapp.data.model.Gasto
import com.google.firebase.firestore.FirebaseFirestore

class GastoRepository {

    private val db = FirebaseFirestore.getInstance()

    fun crearGasto(
        grupoId: String,
        gasto: Gasto,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val gastoId = db.collection("grupos")
            .document(grupoId)
            .collection("gastos")
            .document().id

        val nuevoGasto = gasto.copy(id = gastoId)

        db.collection("grupos")
            .document(grupoId)
            .collection("gastos")
            .document(gastoId)
            .set(nuevoGasto)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }
}
