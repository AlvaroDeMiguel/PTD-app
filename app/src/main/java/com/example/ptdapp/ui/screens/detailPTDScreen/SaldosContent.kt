package com.example.ptdapp.ui.screens.detailPTDScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ptdapp.ui.theme.OpenSansNormal
import com.example.ptdapp.ui.theme.OpenSansSemiCondensed

@Composable
fun MiembrosContent(nombres: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Miembros del grupo:",
            fontSize = 20.sp,
            fontFamily = OpenSansSemiCondensed,
            color = Color.Black
        )

        nombres.forEach { nombre ->
            Text(
                text = "- $nombre",
                fontSize = 16.sp,
                fontFamily = OpenSansNormal,
                color = Color.DarkGray
            )
        }
    }
}

