package com.example.ptdapp.ui.screens.createPTDScreen

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
import com.example.ptdapp.ui.components.CustomBigTextField
import com.example.ptdapp.ui.components.SelectPersonCard


@Composable
fun CreatePTDScreen(
    navController: NavHostController,
) {
    var selectedIcon by remember { mutableStateOf(R.drawable.image) }
    val personas = listOf("Persona 1", "Persona 2", "Persona 3", "Persona 4", "Persona 5", "Persona 6", "Persona 7")
    var checkedStates by remember { mutableStateOf(personas.associateWith { true }) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding() // 👈 importante
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 40.dp, vertical = 20.dp) // 👈 padding correcto
        ) {
            // ⬅️ Botón cancelar DENTRO del scrollable
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
                onIconSelected = { newIcon -> selectedIcon = newIcon }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomBigTextField(
                label = "Descripción",
                placeholder = "Añadir descripción aquí"
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Miembros",
                fontSize = 25.sp,
                fontFamily = Dongle,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(35.dp))

            Text(
                text = "Administrador",
                fontSize = 25.sp,
                fontFamily = Dongle,
                color = Color.Black,
            )

            SelectPersonCard(personas)

            Spacer(modifier = Modifier.height(70.dp))

            CreatePTDButtonComponent(onCreateClick = {
                // TODO: Acción al presionar el botón
            })

            Spacer(modifier = Modifier.height(32.dp)) // 👈 margen final extra
        }
    }
}