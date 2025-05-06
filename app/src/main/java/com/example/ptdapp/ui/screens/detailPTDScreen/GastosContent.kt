package com.example.ptdapp.ui.screens.detailPTDScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ptdapp.data.model.Gasto
import com.example.ptdapp.ui.components.CustomCardGasto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.foundation.lazy.items





@Composable
fun GastosContent(grupoId: String) {
    val db = FirebaseFirestore.getInstance()
    var gastos by remember { mutableStateOf<List<Gasto>>(emptyList()) }

    LaunchedEffect(grupoId) {
        db.collection("grupos")
            .document(grupoId)
            .collection("gastos")
            .orderBy("fecha", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                gastos = result.documents.mapNotNull { it.toObject(Gasto::class.java) }
            }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(gastos) { gasto ->
            val fecha = gasto.fecha ?: Date()

            val dia = SimpleDateFormat("dd", Locale.getDefault()).format(fecha)
            val mes = SimpleDateFormat("MMM", Locale("es")).format(fecha).replaceFirstChar { it.uppercase() }
            val anio = SimpleDateFormat("yyyy", Locale.getDefault()).format(fecha)

            val formattedDate = "$dia $mes. $anio"



            CustomCardGasto(
                fecha = formattedDate,
                nombreGasto = gasto.titulo,
                precioGasto = String.format("%.2f", gasto.cantidad),
                iconoNombre = gasto.iconoNombre
            )
        }

    }
}
