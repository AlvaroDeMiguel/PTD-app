package com.example.ptdapp.ui.components

import android.graphics.Typeface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.ptdapp.R
import com.example.ptdapp.ui.theme.OpenSansNormal
import com.example.ptdapp.ui.theme.OpenSansSemiCondensed

val IconColor = Color(0xFF5F6368) // #5f6368


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCardInicio(
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(19.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFA7D8F5)), // Color de fondo
        modifier = modifier
            .fillMaxWidth()
            .clickable { /* Acción vacía */ }
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

            // Ícono a la derecha
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.chevron_right),
                contentDescription = "Flecha derecha",
                modifier = Modifier.size(43.dp),
                tint = IconColor
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewCustomCardWithIcons() {
    CustomCardInicio(
        text = "Ejemplo de Card",
    )
}
