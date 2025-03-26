package com.example.ptdapp.ui.screens.payment

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun PaymentScreen(
    navController: NavHostController,
    viewModel: PaymentViewModel = viewModel(),
    activity: Activity = LocalContext.current as Activity
) {
    val context = LocalContext.current
    val paymentSuccess = viewModel.paymentSuccess

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Pagar Deuda",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                viewModel.startPayment(context, "10.00", activity)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pagar con Google Pay")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (paymentSuccess != null) {
            Text(
                text = if (paymentSuccess == true) "✅ Pago exitoso" else "❌ Error en el pago",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
