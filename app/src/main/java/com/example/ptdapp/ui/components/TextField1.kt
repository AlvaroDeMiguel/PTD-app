package com.example.ptdapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ptdapp.ui.theme.*

@Composable
fun CustomTextField(placeholder: String,) {
    val textState = remember { mutableStateOf("") }
    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = LightBlue,
            focusedContainerColor = LightBlueDark
        ),
        placeholder = { Text(placeholder) },
        shape = RoundedCornerShape(10.dp)
    )
}

@Preview
@Composable
fun PreviewCustomTextField() {
    CustomTextField(placeholder = "Introduce tu texto")
}


