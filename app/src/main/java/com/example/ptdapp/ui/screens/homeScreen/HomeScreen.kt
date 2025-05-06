package com.example.ptdapp.ui.screens.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ptdapp.ui.components.CustomCardInicio
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*


@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel()
) {
    val grupos by viewModel.gruposUsuario

    LaunchedEffect(Unit) {
        viewModel.cargarGruposDelUsuario()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = grupos, key = { it.id }) { grupo ->
            CustomCardInicio(
                text = grupo.nombre,
                iconoNombre = grupo.iconoNombre,
                onClick = {
                    navController.navigate("detalle_grupo/${grupo.id}")
                }
            )

        }
    }
}


