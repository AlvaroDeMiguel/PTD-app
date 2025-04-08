package com.example.ptdapp.data.repositories

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class WalletRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    fun recargarSaldo(userId: String, amount: Double, onComplete: (Boolean) -> Unit) {
        val userRef = firestore.collection("users").document(userId)

        firestore.runTransaction { transaction ->
            val snapshot = transaction.get(userRef)
            val currentBalance = snapshot.getDouble("wallet.balance") ?: 0.0
            val newBalance = currentBalance + amount
            transaction.update(userRef, "wallet.balance", newBalance)
        }.addOnSuccessListener {
            onComplete(true)
        }.addOnFailureListener {
            onComplete(false)
        }
    }

    fun registrarTransaccion(userId: String, amount: Double) {
        val transaction = hashMapOf(
            "amount" to amount,
            "method" to "GooglePay",
            "timestamp" to FieldValue.serverTimestamp()
        )

        firestore.collection("users")
            .document(userId)
            .collection("transactions")
            .add(transaction)
    }

    fun obtenerSaldoActual(
        userId: String,
        onResult: (Double?) -> Unit
    ) {
        firestore.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                val balance = document.getDouble("wallet.balance")
                onResult(balance)
            }
            .addOnFailureListener {
                onResult(null)
            }
    }
}
