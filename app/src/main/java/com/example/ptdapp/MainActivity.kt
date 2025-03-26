package com.example.ptdapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.ptdapp.ui.navigation.NavGraph
import com.example.ptdapp.ui.authViewmodel.AuthViewModel
import com.example.ptdapp.ui.theme.PTDAppTheme
import androidx.compose.runtime.collectAsState
import com.example.ptdapp.ui.authViewmodel.AuthViewModelFactory
import android.app.Activity
import android.content.Intent
import android.util.Log
import com.example.ptdapp.data.payment.PaymentRepository
import com.example.ptdapp.ui.screens.payment.PaymentViewModel

import com.google.android.gms.wallet.AutoResolveHelper
import com.google.android.gms.wallet.PaymentData


class MainActivity : ComponentActivity() {

    // Callback que se activará desde onActivityResult
    private var onGooglePayResult: ((Boolean) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory())
            val userState = viewModel.user.collectAsState()
            val user = userState.value

            val paymentViewModel: PaymentViewModel = viewModel()

            // Aquí conectamos el resultado de Google Pay al ViewModel
            onGooglePayResult = { success ->
                paymentViewModel.onPaymentResult(success)
            }

            PTDAppTheme {
                NavGraph(navController)
            }
        }
    }

    // Captura del resultado de Google Pay
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PaymentRepository.GOOGLE_PAY_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val paymentData = PaymentData.getFromIntent(data!!)
                    Log.d("GooglePay", "✅ Pago simulado exitoso")
                    onGooglePayResult?.invoke(true)
                }

                Activity.RESULT_CANCELED -> {
                    Log.d("GooglePay", "❌ El usuario canceló el pago")
                    onGooglePayResult?.invoke(false)
                }

                AutoResolveHelper.RESULT_ERROR -> {
                    val status = AutoResolveHelper.getStatusFromIntent(data!!)
                    Log.e("GooglePay", "⚠️ Error: ${status?.statusMessage}")
                    onGooglePayResult?.invoke(false)
                }
            }
        }
    }
}




