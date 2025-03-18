package com.example.ptdapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ptdapp.ui.theme.*
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.ptdapp.R
import androidx.compose.foundation.Image





@Composable
fun CustomTextField(
    label: String,
    placeholder: String,
) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Column() {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 26.sp,
                fontFamily = Dongle,
                color = Color.Black
            ),
        )
        TextField(
            value = textState,
            onValueChange = { textState = it },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LightBlue, // Color cuando no está enfocado
                focusedContainerColor = LightBlueDark, // Color cuando está enfocado
                cursorColor = Gray, // Color del cursor
                focusedTextColor = Gray, // Color del texto cuando está enfocado
                unfocusedTextColor = Gray, // Color del texto cuando no está enfocado
                focusedIndicatorColor = Color.Transparent, // Elimina la línea inferior cuando está enfocado
                unfocusedIndicatorColor = Color.Transparent, // Elimina la línea inferior cuando no está enfocado
                disabledIndicatorColor = Color.Transparent // Elimina la línea inferior cuando está deshabilitado
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        fontFamily = OpenSansNormal,
                        fontSize = 18.sp,
                        color = Gray
                    )
                )
            },
            textStyle = TextStyle(
                fontFamily = OpenSansNormal,
                fontSize = 18.sp,
                color = Color.Black
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CustomBigTextField(
    label: String,
    placeholder: String,
) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Column() {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 25.sp,
                fontFamily = Dongle,
                color = Color.Black
            ),
        )
        TextField(
            value = textState,
            onValueChange = { textState = it },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LightBlue, // Color cuando no está enfocado
                focusedContainerColor = LightBlueDark, // Color cuando está enfocado
                cursorColor = Gray, // Color del cursor
                focusedTextColor = Gray, // Color del texto cuando está enfocado
                unfocusedTextColor = Gray, // Color del texto cuando no está enfocado
                focusedIndicatorColor = Color.Transparent, // Elimina la línea inferior cuando está enfocado
                unfocusedIndicatorColor = Color.Transparent, // Elimina la línea inferior cuando no está enfocado
                disabledIndicatorColor = Color.Transparent // Elimina la línea inferior cuando está deshabilitado
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        fontFamily = OpenSansNormal,
                        fontSize = 15.sp,
                        color = Gray
                    )
                )
            },
            textStyle = TextStyle(
                fontFamily = OpenSansNormal,
                fontSize = 15.sp,
                color = Color.Black
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().height(150.dp)
        )
    }
}

@Composable
fun CustomTextFieldPassword(
    label: String,
    placeholder: String,
) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) } // Estado para alternar visibilidad
    Column() {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 26.sp,
//                fontWeight = FontWeight.Bold,
                fontFamily = Dongle,
                color = Color.Black
            ),
        )
        TextField(
            value = textState,
            onValueChange = { textState = it },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LightBlue, // Color cuando no está enfocado
                focusedContainerColor = LightBlueDark, // Color cuando está enfocado
                cursorColor = Gray, // Color del cursor
                focusedTextColor = Gray, // Color del texto cuando está enfocado
                unfocusedTextColor = Gray, // Color del texto cuando no está enfocado
                focusedIndicatorColor = Color.Transparent, //  Elimina la línea inferior cuando está enfocado
                unfocusedIndicatorColor = Color.Transparent //  Elimina la línea inferior cuando no está enfocado
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        fontFamily = OpenSansNormal,
                        fontSize = 18.sp,
                        color = Gray
                    )
                )
            },
            textStyle = TextStyle(
                fontFamily = OpenSansNormal,
                fontSize = 18.sp,
                color = Color.Black
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Icon(
                    painter = painterResource(
                        id = if (passwordVisible) R.drawable.visibility else R.drawable.visibility_off
                    ),
                    contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                    tint = Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { passwordVisible = !passwordVisible } // Alterna la visibilidad
                )
            }
        )
    }
}


@Composable
fun CustomTextFieldIcon(
    label: String,
    placeholder: String,
    selectedIcon: Int, // Ícono seleccionado como recurso drawable
    onIconSelected: (Int) -> Unit // Callback al seleccionar un ícono
) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    var expanded by remember { mutableStateOf(false) } // Estado del menú

    val iconOptions = listOf(
        R.drawable.image, R.drawable.restaurant, R.drawable.credit_card
    )

    Column {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 25.sp,
                fontFamily = Dongle,
                color = Color.Black
            ),
        )
        TextField(
            value = textState,
            onValueChange = { textState = it },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LightBlue,
                focusedContainerColor = LightBlueDark,
                cursorColor = Gray,
                focusedTextColor = Gray,
                unfocusedTextColor = Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = OpenSansNormal,
                        color = Gray
                    )
                )
            },
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontFamily = OpenSansNormal,
                color = Color.Black
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Box {
                    Image(
                        painter = painterResource(id = selectedIcon),
                        contentDescription = "Seleccionar icono",
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { expanded = true } // Hace el icono clickeable
                    )

                    // Menú desplegable de iconos
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        iconOptions.forEach { icon ->
                            DropdownMenuItem(
                                text = { Text("") },
                                onClick = {
                                    onIconSelected(icon) // Cambia el ícono seleccionado
                                    expanded = false
                                },
                                leadingIcon = {
                                    Image(
                                        painter = painterResource(id = icon),
                                        contentDescription = "Icono",
                                        modifier = Modifier.size(28.dp),
                                    )
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun CustomTextFieldFixedIcon(
    label: String,
    placeholder: String,
) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Column {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 25.sp,
                fontFamily = Dongle,
                color = Color.Black
            ),
        )
        TextField(
            value = textState,
            onValueChange = { textState = it },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LightBlue,
                focusedContainerColor = LightBlueDark,
                cursorColor = Color.Gray,
                focusedTextColor = Color.Gray,
                unfocusedTextColor = Color.Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = OpenSansNormal,
                        color = Gray
                    )
                )
            },
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontFamily = OpenSansNormal,
                color = Color.Black
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.euro_symbol),
                    contentDescription = "euro",
                    modifier = Modifier.size(28.dp),
                    tint = Color.Black
                )
            }
        )
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewCustomTextField() {

    PTDAppTheme {
        var selectedIcon by remember { mutableStateOf(R.drawable.image) }

        CustomTextFieldIcon(
            label = "Categoría",
            placeholder = "Selecciona una categoría",
            selectedIcon = selectedIcon,
            onIconSelected = { newIcon -> selectedIcon = newIcon }
        )
    }
}




