package com.example.ptdapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ptdapp.ui.components.CustomCardInicio
import com.example.ptdapp.ui.components.CustomTextField
import com.example.ptdapp.ui.components.CustomTextFieldPassword
import com.example.ptdapp.ui.theme.PTDAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PTDAppTheme{
                CenteredTextFields()
            }
        }
    }
}


@Composable
fun CenteredTextFields() {
    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        CustomTextField(label = "Correo electrónico", placeholder = "ejemplo@gmail.com")
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextFieldPassword(label = "Contraseña",placeholder = "Introduce contraseña")
        Spacer(modifier = Modifier.height(16.dp))
        CustomCardInicio(text = "Card")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PTDAppTheme {

    }
}