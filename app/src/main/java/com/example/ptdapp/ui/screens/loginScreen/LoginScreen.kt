package com.example.ptdapp.ui.screens.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ptdapp.R
import com.example.ptdapp.ui.components.CustomTextField
import com.example.ptdapp.ui.components.CustomTextFieldPassword
import com.example.ptdapp.ui.components.LoginButtonComponent
import com.example.ptdapp.ui.navigation.Destinations
import com.example.ptdapp.ui.theme.BlueLight
import com.example.ptdapp.ui.theme.Dongle
import com.example.ptdapp.ui.theme.Gray


@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Label que redirecciona a la screen registrarse
        RegisterLabel (
            onRegisterClick = {
                navController.navigate(Destinations.REGISTER_SCREEN)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo_transparente), // Reemplaza 'logo' con el nombre correcto del recurso
            contentDescription = "Logo de la aplicación",
            modifier = Modifier
                .width(300.dp)  // Ajusta el tamaño según necesites
                .height(160.dp),   // Ajusta el tamaño según necesites

        )
        Spacer(modifier = Modifier.height(32.dp))
        // Línea divisoria
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp) // Grosor de la línea
                .background(BlueLight) // Color azul de la línea
        )
        // Campo de correo
        Spacer(modifier = Modifier.height(117.dp))
        CustomTextField(label = "Correo electrónico", placeholder = "correo@gmail.com")
        // Campo de contraseña con icono para mostrar/ocultar
        Spacer(modifier = Modifier.height(10.dp))
        CustomTextFieldPassword(label = "Contraseña", placeholder = "********")
        // Enlace para recuperar contraseña
        ForgotPasswordLabel(
            onForgotPasswordClick = {
                //TODO
            }
        )
        Spacer(modifier = Modifier.height(117.dp))

        // Botón de inicio de sesión
        Spacer(modifier = Modifier.height(24.dp))
        LoginButtonComponent(
            onLoginClick = {
            //TODO
            }
        )
    }
}

@Composable
fun ForgotPasswordLabel(onForgotPasswordClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End // Alinea el texto a la derecha
    ) {
        Text(
            text = "He olvidado mi contraseña",
            style = TextStyle(
                fontFamily = Dongle,
                fontSize = 20.sp,
                color = Gray
            ),
            modifier = Modifier.clickable { onForgotPasswordClick() }
        )
    }
}

@Composable
fun RegisterLabel(onRegisterClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End // Alinea el texto a la derecha
    ) {
        Text(
            text = "Registrarse",
            style = TextStyle(
                fontFamily = Dongle,
                fontSize = 30.sp,
                color = BlueLight
            ),
            modifier = Modifier.clickable { onRegisterClick() }
        )
    }
}





