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
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { passwordVisible = !passwordVisible } // Alterna la visibilidad
                )
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCustomTextField() {

    PTDAppTheme {
        CustomTextField(
            label = "Usuario", placeholder = "Introduce tu usuario"
        )
    }
}




