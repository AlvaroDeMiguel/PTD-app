package com.example.ptdapp.ui.screens.notificationScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ptdapp.data.repositories.NotificationsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class NotificationsViewModel : ViewModel() {

    private val repository = NotificationsRepository()

    private val _notifications = MutableStateFlow<List<Notification>>(emptyList())
    val notifications: StateFlow<List<Notification>> = _notifications

    init {
        viewModelScope.launch {
            repository.getNotificationsFlow().collect {
                _notifications.value = it
            }
        }
    }

    fun deleteNotification(id: String) {
        viewModelScope.launch {
            repository.deleteNotification(id)
        }
    }
}

data class Notification(
    val id: String = "",
    val title: String = "",
    val description: String = ""
)

