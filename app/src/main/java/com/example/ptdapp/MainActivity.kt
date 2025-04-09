package com.example.ptdapp

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.ptdapp.data.payment.PaymentRepository
import com.example.ptdapp.ui.authViewmodel.AuthViewModel
import com.example.ptdapp.ui.authViewmodel.AuthViewModelFactory
import com.example.ptdapp.ui.navigation.NavGraph
import com.example.ptdapp.ui.screens.walletScreen.WalletViewModel
import com.example.ptdapp.ui.theme.PTDAppTheme
import com.google.android.gms.wallet.AutoResolveHelper
import com.google.android.gms.wallet.PaymentData

class MainActivity : ComponentActivity() {

    private var onGooglePayResult: ((Boolean) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory())
            val walletViewModel = viewModel<WalletViewModel>()
            onGooglePayResult = { success ->
                if (success) {
                    walletViewModel.confirmarPagoExitoso()
                }
            }

            PTDAppTheme {
                NavGraph(navController, walletViewModel = walletViewModel)
            }
        }
    }

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
                    Log.e("GooglePay", "⚠️ Error en Google Pay: ${status?.statusMessage}")
                    onGooglePayResult?.invoke(false)
                }
            }
        }
    }
}
