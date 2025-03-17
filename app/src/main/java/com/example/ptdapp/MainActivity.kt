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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.ptdapp.ui.components.CustomAlertDialog
import com.example.ptdapp.ui.components.CustomCardGasto
import com.example.ptdapp.ui.components.CustomCardInicio
import com.example.ptdapp.ui.components.CustomCardSaldo
import com.example.ptdapp.ui.components.CustomTextField
import com.example.ptdapp.ui.components.CustomTextFieldPassword
import com.example.ptdapp.ui.components.LoginButtonComponent
import com.example.ptdapp.ui.navigation.NavGraph
import com.example.ptdapp.ui.theme.PTDAppTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() // Crear NavController
            val isUserLoggedIn = FirebaseAuth.getInstance().currentUser != null // Verifica sesión
            val isUserLoggedInFake = false
            PTDAppTheme {
                NavGraph(navController, isUserLoggedInFake)
            }
        }
    }
}


@Composable
fun CenteredTextFields() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        CustomTextFieldPassword(label = "Contraseña", placeholder = "Contraseña")
        Spacer(modifier = Modifier.height(24.dp))
        LoginButtonComponent(
            onLoginClick = { TODO() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PTDAppTheme {

    }
}