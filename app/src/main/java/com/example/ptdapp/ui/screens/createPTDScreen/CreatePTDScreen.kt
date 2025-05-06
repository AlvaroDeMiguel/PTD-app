package com.example.ptdapp.ui.screens.createPTDScreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ptdapp.R
import com.example.ptdapp.ui.components.CheckboxCard
import com.example.ptdapp.ui.components.CreatePTDButtonComponent
import com.example.ptdapp.ui.components.CustomTextFieldFixedIcon
import com.example.ptdapp.ui.components.CustomTextFieldIcon
import com.example.ptdapp.ui.theme.BlueLight
import com.example.ptdapp.ui.theme.Dongle
import com.example.ptdapp.ui.theme.OpenSansNormal
import com.example.ptdapp.ui.theme.OpenSansSemiCondensed
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ptdapp.ui.components.CustomBigTextField
import com.example.ptdapp.ui.components.SelectPersonCard
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun CreatePTDScreen(
    navController: NavHostController,
    viewModel: CreatePTDViewModel = viewModel()
) {
    var selectedIcon by remember { mutableStateOf(R.drawable.image) }
    var nombreGrupo by remember { mutableStateOf("") }
    var descripcionGrupo by remember { mutableStateOf("") }
    val personas = listOf("Persona 1", "Persona 2", "Persona 3")
    var adminSeleccionado by remember { mutableStateOf(personas.first()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 40.dp, vertical = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable(onClick = { navController.popBackStack() })
                    .padding(top = 5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back_ios),
                    contentDescription = "Cancelar",
                    modifier = Modifier.size(24.dp),
                    tint = BlueLight
                )
                Text(
                    text = "Cancelar",
                    color = BlueLight,
                    fontSize = 20.sp,
                    fontFamily = OpenSansNormal,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Crear PTD",
                fontSize = 28.sp,
                fontFamily = OpenSansSemiCondensed,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomTextFieldIcon(
                label = "Nombre",
                placeholder = "Añadir nombre aquí",
                selectedIcon = selectedIcon,
                onIconSelected = { newIcon -> selectedIcon = newIcon },
                onTextChanged = { nombreGrupo = it } // Implementa este callback en tu componente
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomBigTextField(
                label = "Descripción",
                placeholder = "Añadir descripción aquí",
                onTextChanged = { descripcionGrupo = it } // Implementa este callback en tu componente
            )


            Spacer(modifier = Modifier.height(70.dp))

            CreatePTDButtonComponent(
                onCreateClick = {
                    viewModel.crearPTD(
                        nombre = nombreGrupo,
                        descripcion = descripcionGrupo,
                        iconoId = selectedIcon, // 👈 se pasa como Int y el ViewModel lo transforma a String
                        onSuccess = {
                            navController.popBackStack()
                        },
                        onFailure = { error ->
                            Log.e("CreatePTD", "Error al crear grupo: ${error.message}")
                        }
                    )
                }
            )




            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
