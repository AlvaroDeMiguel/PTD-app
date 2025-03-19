package com.example.ptdapp.ui.screens.profileScreen


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ptdapp.ui.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: AuthViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Perfil del Usuario",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.logout() // Cierra sesión y redirige automáticamente a LoginScreen
            }
        ) {
            Text(text = "Cerrar Sesión")
        }
    }
}
