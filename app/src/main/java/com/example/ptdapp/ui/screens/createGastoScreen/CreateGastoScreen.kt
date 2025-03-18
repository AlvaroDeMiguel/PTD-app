package com.example.ptdapp.ui.screens.createGastoScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.clickable
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
import com.example.ptdapp.ui.components.CustomTextFieldFixedIcon
import com.example.ptdapp.ui.components.CustomTextFieldIcon
import com.example.ptdapp.ui.theme.BlueLight
import com.example.ptdapp.ui.theme.Dongle
import com.example.ptdapp.ui.theme.OpenSansNormal
import com.example.ptdapp.ui.theme.OpenSansSemiCondensed
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.ptdapp.ui.components.CreateGastoButtonComponent
import com.example.ptdapp.ui.components.SelectPersonCard

@Composable
fun CreateGastoScreen(navController: NavHostController) {
// ✅ Variable de estado para el icono seleccionado
    var selectedIcon by remember { mutableStateOf(R.drawable.image) }

    // ✅ Lista de personas
    val personas = listOf(
        "Persona 1",
        "Persona 2",
        "Persona 3",
        "Persona 4",
        "Persona 5",
        "Persona 6",
        "Persona 7"
    )

    // ✅ Estado global para los checkboxes
    var checkedStates by remember { mutableStateOf(personas.associateWith { true }) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Botón de cancelar con su propio padding
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = { navController.popBackStack() })
                .padding(top = 25.dp, start = 10.dp)
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp)
                .align(Alignment.TopCenter)
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            // Título de la pantalla
            Text(
                text = "Añadir Gasto",
                fontSize = 28.sp,
                fontFamily = OpenSansSemiCondensed,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(30.dp))

            // Campo de título
            CustomTextFieldIcon(
                label = "Título",
                placeholder = "Añadir título aquí",
                selectedIcon = selectedIcon,
                onIconSelected = { newIcon ->
                    selectedIcon = newIcon
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de precio
            CustomTextFieldFixedIcon(
                label = "Precio",
                placeholder = "0 , 00",
            )
            Spacer(modifier = Modifier.height(25.dp))

            // Campo a dividir
            Text(
                text = "A dividir entre",
                fontSize = 25.sp,
                fontFamily = Dongle,
                color = Color.Black,
            )

            // ✅ LazyColumn para mostrar la lista de personas con CheckboxCard
            LazyColumn(
                modifier = Modifier.fillMaxHeight(0.4f), // Ajusta la altura si es necesario
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(personas) { persona ->
                    CheckboxCard(
                        persona = persona,
                        checked = checkedStates[persona] ?: true, // Obtener el estado actual
                        onCheckedChange = { isChecked ->
                            checkedStates = checkedStates.toMutableMap().apply {
                                put(persona, isChecked)
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(35.dp))

            //Card elegir quien paga
            Text(
                text = "Pagado por",
                fontSize = 25.sp,
                fontFamily = Dongle,
                color = Color.Black,
            )
            SelectPersonCard(personas)
            // Botón de añadir
            Spacer(modifier = Modifier.height(70.dp))

            CreateGastoButtonComponent(onCreateClick = {
                // TODO: Acción al presionar el botón
            })
        }
    }
}