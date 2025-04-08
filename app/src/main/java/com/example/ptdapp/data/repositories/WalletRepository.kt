package com.example.ptdapp.data.repositories

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class WalletRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {

    // 🔄 Flow para observar el saldo en tiempo real
    fun getSaldoFlow(): Flow<Double?> = callbackFlow {
        val userId = auth.currentUser?.uid ?: run {
            close()
            return@callbackFlow
        }

        val ref = firestore.collection("users").document(userId)

        val listener = ref.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("WalletRepository", "Error escuchando saldo", error)
                trySend(null)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val balance = snapshot.getDouble("wallet.balance")
                trySend(balance)
            } else {
                trySend(null)
            }
        }

        awaitClose { listener.remove() }
    }

    // 💳 Recargar saldo (crea documento automáticamente si no existe)
    suspend fun recargarSaldo(amount: Double): Boolean {
        val userId = auth.currentUser?.uid ?: return false
        val userRef = firestore.collection("users").document(userId)

        return try {
            firestore.runTransaction { transaction ->
                val snapshot = transaction.get(userRef)
                if (!snapshot.exists()) {
                    transaction.set(userRef, mapOf("wallet" to mapOf("balance" to amount)))
                } else {
                    val currentBalance = snapshot.getDouble("wallet.balance") ?: 0.0
                    transaction.update(userRef, "wallet.balance", currentBalance + amount)
                }
            }.await()

            registrarTransaccion(userId, amount)
            true
        } catch (e: Exception) {
            Log.e("WalletRepository", "❌ Error Firebase", e)
            false
        }
    }


    // 📝 Registrar transacción dentro del usuario (subcolección)
    private suspend fun registrarTransaccion(userId: String, amount: Double) {
        val transaccion = mapOf(
            "amount" to amount,
            "method" to "GooglePay",
            "timestamp" to FieldValue.serverTimestamp()
        )

        firestore.collection("users")
            .document(userId)
            .collection("transactions")
            .add(transaccion)
            .await()
    }

    // 🧾 Obtener historial de transacciones en tiempo real (Opcional pero recomendado)
    fun getTransaccionesFlow(): Flow<List<Map<String, Any>>> = callbackFlow {
        val userId = auth.currentUser?.uid ?: run {
            close()
            return@callbackFlow
        }

        val transaccionesRef = firestore.collection("users")
            .document(userId)
            .collection("transactions")
            .orderBy("timestamp")

        val listener = transaccionesRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("WalletRepository", "Error escuchando transacciones", error)
                trySend(emptyList())
                return@addSnapshotListener
            }

            val transacciones = snapshot?.documents?.mapNotNull { it.data } ?: emptyList()
            trySend(transacciones)
        }

        awaitClose { listener.remove() }
    }
}
