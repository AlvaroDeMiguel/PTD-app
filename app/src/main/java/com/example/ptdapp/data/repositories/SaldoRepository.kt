package com.example.ptdapp.data.repositories

import com.example.ptdapp.data.model.Gasto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SaldoRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun obtenerGastos(grupoId: String): List<Gasto> {
        return try {
            val snapshot = db.collection("grupos")
                .document(grupoId)
                .collection("gastos")
                .get()
                .await()

            snapshot.documents.mapNotNull { it.toObject(Gasto::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun calcularSaldos(gastos: List<Gasto>): Map<String, Double> {
        val saldos = mutableMapOf<String, Double>()

        for (gasto in gastos) {
            val pagador = gasto.pagadoPor
            val cantidadPorPersona = gasto.cantidad / gasto.divididoEntre.size

            // Suma al que pagó
            saldos[pagador] = saldos.getOrDefault(pagador, 0.0) + gasto.cantidad

            // Resta a los que deben
            gasto.divididoEntre.forEach { uid ->
                saldos[uid] = saldos.getOrDefault(uid, 0.0) - cantidadPorPersona
            }
        }

        return saldos
    }

    suspend fun obtenerNombresUsuarios(miembros: List<String>): Map<String, String> {
        val nombresMap = mutableMapOf<String, String>()
        val db = FirebaseFirestore.getInstance()

        miembros.forEach { uid ->
            try {
                val doc = db.collection("users").document(uid).get().await()
                val nombre = doc.getString("nombre") ?: uid
                nombresMap[uid] = nombre
            } catch (_: Exception) {
                nombresMap[uid] = uid // fallback
            }
        }

        return nombresMap
    }
}

