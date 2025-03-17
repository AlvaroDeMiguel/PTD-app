package com.example.ptdapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.TextStyle
import com.example.ptdapp.ui.theme.BlueLight
import com.example.ptdapp.ui.theme.OpenSansSemiCondensed


@Composable
fun LoginButtonComponent(onLoginClick: () -> Unit) {
    Button(
        onClick = {
            onLoginClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = BlueLight),
        shape = RoundedCornerShape(10.dp), // Bordes redondeados
        modifier = Modifier
            .fillMaxWidth() // 80% del ancho de la pantalla
            .height(50.dp) // Altura del botón
    ) {
        Text(
            text = "Iniciar Sesión",
            style = TextStyle(
                fontFamily = OpenSansSemiCondensed,
                fontSize = 19.sp,
                color = Color.Black
            )
        )
    }
}

@Composable
fun RegisterButtonComponent(onLoginClick: () -> Unit) {
    Button(
        onClick = {
            onLoginClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = BlueLight),
        shape = RoundedCornerShape(10.dp), // Bordes redondeados
        modifier = Modifier
            .fillMaxWidth(0.8f) // 80% del ancho de la pantalla
            .height(50.dp) // Altura del botón
    ) {
        Text(
            text = "Registrarse",
            fontSize = 19.sp,
            style = TextStyle(
                fontFamily = OpenSansSemiCondensed,
                fontSize = 18.sp,
                color = Color.Black
            )
        )
    }
}
