package com.example.ptdapp.ui.screens.payment

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.ptdapp.data.payment.PaymentRepository

class PaymentViewModel(
    private val repository: PaymentRepository = PaymentRepository() // o inyectado
) : ViewModel() {

    // Estado del pago: null, true (éxito), false (error)
    var paymentSuccess by mutableStateOf<Boolean?>(null)
        private set

    // Lanza Google Pay
    fun startPayment(context: Context, amount: String, activity: Activity) {
        repository.launchGooglePay(context, amount, activity) {
            paymentSuccess = it // <- También podrías manejarlo directo si no usas onPaymentResult separado
        }
    }

    // Función llamada desde MainActivity con el resultado
    fun onPaymentResult(success: Boolean) {
        paymentSuccess = success
    }
}


