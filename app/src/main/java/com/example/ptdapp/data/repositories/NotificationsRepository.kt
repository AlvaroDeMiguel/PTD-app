package com.example.ptdapp.data.repositories

import com.example.ptdapp.ui.screens.notificationScreen.Notification
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


class NotificationsRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun getNotificationsFlow(): Flow<List<Notification>> = callbackFlow {
        val userId = auth.currentUser?.uid ?: return@callbackFlow
        val ref = firestore.collection("users").document(userId).collection("notifications")

        val listener = ref.addSnapshotListener { snapshot, _ ->
            if (snapshot != null) {
                val notifications = snapshot.documents.mapNotNull { it.toObject(Notification::class.java)?.copy(id = it.id) }
                trySend(notifications)
            }
        }

        awaitClose { listener.remove() }
    }

    suspend fun deleteNotification(id: String) {
        val userId = auth.currentUser?.uid ?: return
        firestore.collection("users").document(userId).collection("notifications").document(id).delete().await()
    }
}
