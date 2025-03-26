package com.example.ptdapp.ui.screens.notificationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ptdapp.ui.components.LogoSpinner
import com.example.ptdapp.ui.components.NotificationCard

@Composable
fun NotificationScreen(
    navController: NavHostController,
    viewModel: NotificationsViewModel = viewModel()
) {
    val notifications by viewModel.notifications.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(25.dp)) {

            // Botón de prueba
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Añadir test",
                    color = Color.Blue,
                    modifier = Modifier
                        .background(Color.LightGray)
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .clickable { viewModel.addTestNotification() }
                )
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(notifications) { notification ->
                    NotificationCard(
                        title = notification.title,
                        description = notification.description,
                        onDelete = { viewModel.deleteNotification(notification.id) }
                    )
                }
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                LogoSpinner()
            }
        }
    }
}


