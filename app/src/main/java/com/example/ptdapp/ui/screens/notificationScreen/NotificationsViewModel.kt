package com.example.ptdapp.ui.screens.notificationScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ptdapp.data.repositories.NotificationsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class NotificationsViewModel : ViewModel() {

    private val repository = NotificationsRepository()

    private val _notifications = MutableStateFlow<List<Notification>>(emptyList())
    val notifications: StateFlow<List<Notification>> = _notifications

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            repository.getNotificationsFlow().collect { notificationList ->
                _isLoading.value = false // Esto DEBE ir aquí arriba
                _notifications.value = notificationList
            }
        }
    }


    fun deleteNotification(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.deleteNotification(id)
            _isLoading.value = false
        }
    }

    //FUNCION PARA CREAR NOTIFICACIONES DE PRUEBA
    fun addTestNotification() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val ref = FirebaseFirestore.getInstance()
            .collection("users").document(userId)
            .collection("notifications")

        val notification = mapOf(
            "title" to "Notificación de prueba",
            "description" to "Esto es solo un test visual"
        )

        viewModelScope.launch {
            try {
                ref.add(notification).await()
                Log.d("AddNotification", "Notificación añadida correctamente")
            } catch (e: Exception) {
                Log.e("AddNotification", "Error al añadir notificación", e)
            }
        }
    }
}


data class Notification(
    val id: String = "",
    val title: String = "",
    val description: String = ""
)

