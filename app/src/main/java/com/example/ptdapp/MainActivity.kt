package com.example.ptdapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ptdapp.ui.components.CustomCardGasto
import com.example.ptdapp.ui.components.CustomCardInicio
import com.example.ptdapp.ui.components.CustomCardSaldo
import com.example.ptdapp.ui.components.CustomTextField
import com.example.ptdapp.ui.components.CustomTextFieldPassword
import com.example.ptdapp.ui.theme.PTDAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PTDAppTheme{
                CenteredTextFields()
            }
        }
    }
}


@Composable
fun CenteredTextFields() {
    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        CustomCardInicio(text = "Card")
        Spacer(modifier = Modifier.height(16.dp))
        CustomCardGasto(fecha = "2 Ene. 2025", nombreGasto = "Gasto 1", precioGasto = "0,00")
        Spacer(modifier = Modifier.height(16.dp))
        CustomCardSaldo(nombreGasto = "Gasto 1", precioGasto = "0,00")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PTDAppTheme {

    }
}