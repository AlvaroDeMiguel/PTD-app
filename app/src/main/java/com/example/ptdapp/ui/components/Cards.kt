package com.example.ptdapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import com.example.ptdapp.R
import com.example.ptdapp.ui.theme.OpenSansSemiCondensed
import com.example.ptdapp.ui.theme.OpenSauce
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import com.example.ptdapp.ui.theme.OpenSansNormal


val IconColor = Color(0xFF5F6368)
val CardColor = Color(0xFFA7D8F5)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCardInicio(
    text: String,
) {
    Card(
        shape = RoundedCornerShape(19.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor), // Color de fondo
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /*TODO navegar a la vista de detalle de ptd */ }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start // Alinea los elementos a la izquierda
        ) {
            // Ícono a la izquierda
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.add_photo),
                contentDescription = "Ícono personalizado",
                modifier = Modifier.size(43.dp),
                tint = IconColor
            )

            // Espaciado entre el ícono y el texto
            Spacer(modifier = Modifier.width(25.dp))

            // Texto más cerca del icono
            Text(
                text = text,
                fontSize = 21.sp,
                fontFamily = OpenSansSemiCondensed,
                color = Color.Black,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.weight(1f)) // Empuja el icono de la derecha

            // Texto a la derecha
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.chevron_right),
                contentDescription = "Flecha derecha",
                modifier = Modifier.size(43.dp),
                tint = IconColor
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCardGasto(
    fecha: String,
    nombreGasto: String,
    precioGasto: String,
) {
    Column() {
        // Texto que indica la fecha
        Text(
            text = fecha,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = OpenSauce,
                color = Color.Black
            ),
        )
        Card(
            shape = RoundedCornerShape(19.dp),
            colors = CardDefaults.cardColors(containerColor = CardColor), // Color de fondo
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /*TODO navegar a la vista de detalle de gasto */ }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start // Alinea los elementos a la izquierda
            ) {
                // Ícono a la izquierda
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.add_photo),
                    contentDescription = "Ícono personalizado",
                    modifier = Modifier.size(43.dp),
                    tint = IconColor
                )

                // Espaciado entre el ícono y el texto
                Spacer(modifier = Modifier.width(25.dp))

                // Texto más cerca del icono
                Text(
                    text = nombreGasto,
                    fontSize = 21.sp,
                    fontFamily = OpenSansSemiCondensed,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.weight(1f)) // Empuja el icono de la derecha

                // texto del precio
                Text(
                    fontSize = 24.sp,
                    text = "$precioGasto €",
                    fontFamily = OpenSauce,
                    color = Color.Black,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCardSaldo(
    nombrePersona: String,
    gastoPersona: String,
) {
    Card(
        shape = RoundedCornerShape(19.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor), // Color de fondo
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start // Alinea los elementos a la izquierda
        ) {
            // Texto del nombre de la persona
            Text(
                text = nombrePersona,
                fontSize = 22.sp,
                fontFamily = OpenSansSemiCondensed,
                color = Color.Black,
                textAlign = TextAlign.Start,

            )
            Spacer(modifier = Modifier.weight(1f)) // Empuja el icono de la derecha
            // texto del gasto de la persona
            Text(
                fontSize = 20.sp,
                text = "$gastoPersona €",
                fontFamily = OpenSauce,
                color = Color.Black,
            )
        }
    }
}



@Composable
fun CheckboxCard(
    persona: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 10.dp)
        ) {
            Text(
                fontSize = 15.sp,
                text = persona,
                fontFamily = OpenSansNormal,
                color = Color.Black,
            )
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange // Sincroniza con el estado global
            )
        }
    }
}


@Composable
fun SelectPersonCard(personas: List<String>) {
    // ✅ Estado para recordar la persona seleccionada
    var selectedPerson by remember { mutableStateOf(personas.firstOrNull() ?: "Seleccionar persona") }
    var expanded by remember { mutableStateOf(false) } // Estado para el menú desplegable

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true } // Abre el menú al hacer clic
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = selectedPerson,
                fontSize = 15.sp,
                fontFamily = OpenSansNormal,
                color = Color.Black,
            )
            Icon(
                painter = painterResource(id = android.R.drawable.arrow_down_float),
                contentDescription = "Abrir selección",
                tint = Color.Black
            )
        }
    }

    // ✅ DropdownMenu con la lista de personas
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        personas.forEach { persona ->
            DropdownMenuItem(
                text = { Text(persona) },
                onClick = {
                    selectedPerson = persona // ✅ Guarda la selección
                    expanded = false
                }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewCustomCardWithIcons() {
}
