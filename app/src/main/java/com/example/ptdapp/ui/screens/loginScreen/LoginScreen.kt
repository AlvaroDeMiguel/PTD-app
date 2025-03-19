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
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ptdapp.ui.viewmodel.AuthViewModel


@Composable
fun LoginScreen(navController: NavHostController, viewModel: AuthViewModel = viewModel()) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val user by viewModel.user.collectAsState()

    LaunchedEffect(user) {
        if (user != null) {
            Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
            navController.navigate(Destinations.MAIN_SCREEN)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        RegisterLabel { navController.navigate(Destinations.REGISTER_SCREEN) }
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_transparente),
            contentDescription = "Logo de la aplicación",
            modifier = Modifier
                .width(300.dp)
                .height(160.dp),
        )
        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(BlueLight)
        )

        Spacer(modifier = Modifier.height(117.dp))

        CustomTextField(
            label = "Correo electrónico",
            placeholder = "correo@gmail.com",
            value = email
        ) { email = it }

        Spacer(modifier = Modifier.height(10.dp))

        CustomTextFieldPassword(
            label = "Contraseña",
            placeholder = "********",
            value = password
        ) { password = it }

        ForgotPasswordLabel(viewModel)

        Spacer(modifier = Modifier.height(20.dp))

        errorMessage?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = Dongle,
                    color = MaterialTheme.colorScheme.error
                )
            )
        }

        Spacer(modifier = Modifier.height(97.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            LoginButtonComponent {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.login(email, password)
                } else {
                    Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}


@Composable
fun ForgotPasswordLabel(viewModel: AuthViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = "He olvidado mi contraseña",
            style = TextStyle(fontFamily = Dongle, fontSize = 20.sp, color = Gray),
            modifier = Modifier.clickable { showDialog = true }
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Recuperar contraseña") },
            text = {
                Column {
                    Text("Introduce tu correo para recibir un enlace de recuperación.")
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        label = "Correo electrónico",
                        placeholder = "correo@gmail.com",
                        value = email,
                        onValueChange = { email = it }
                    )
                    errorMessage?.let {
                        Text(text = it, color = MaterialTheme.colorScheme.error)
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (email.isNotEmpty()) {
                            viewModel.resetPassword(email)
                            Toast.makeText(context, "Correo enviado si el usuario existe", Toast.LENGTH_LONG).show()
                            showDialog = false
                        } else {
                            Toast.makeText(context, "Introduce un correo válido", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text("Enviar")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
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