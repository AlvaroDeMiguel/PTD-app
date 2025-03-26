package com.example.ptdapp.ui.screens.notificationScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ptdapp.ui.components.NotificationCard

@Composable
fun NotificationScreen(
    navController: NavHostController,
    viewModel: NotificationsViewModel = viewModel()
) {
    val notifications by viewModel.notifications.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        LazyColumn {
            items(notifications) { notification ->
                NotificationCard(
                    title = notification.title,
                    description = notification.description,
                    onDelete = { viewModel.deleteNotification(notification.id) }
                )
            }
        }
    }
}