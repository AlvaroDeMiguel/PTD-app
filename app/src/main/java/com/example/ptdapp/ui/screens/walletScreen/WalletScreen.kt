package com.example.ptdapp.ui.screens.walletScreen

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ptdapp.data.payment.PaymentRepository
import com.example.ptdapp.ui.components.IngresarButtonComponent
import com.example.ptdapp.ui.components.NumericTextField
import com.example.ptdapp.ui.theme.BlueLight
import com.example.ptdapp.ui.theme.Gray
import com.example.ptdapp.ui.theme.Green
import com.example.ptdapp.ui.theme.OpenSansSemiCondensed
import com.example.ptdapp.ui.theme.OpenSauce


@Composable
fun WalletScreen(
    navController: NavHostController,
    walletViewModel: WalletViewModel
) {
    var customAmount by remember { mutableStateOf("") }
    val context = LocalContext.current
    val activity = context as Activity
    val paymentRepository = remember { PaymentRepository() }

    val recargaExitosa by walletViewModel.recargaExitosa.collectAsState()
    val saldo by walletViewModel.saldo.collectAsState()

    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        //BOX DEL SALDO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .border(width = 1.dp, color = Gray)
                .background(Color.White)
                .padding(20.dp), // Espaciado interno
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = formatPrice1(saldo ?: 0.00),
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontFamily = OpenSauce,
                        color = Color.Black
                    )
                )
                Text(
                    text = "Saldo disponible",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = OpenSansSemiCondensed,
                        fontWeight = FontWeight.Bold,
                        color = BlueLight
                    )
                )
            }
        }

        // BOX DEL DEUDAS DIVIDIDO EN TRES PARTES
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .border(width = 1.dp, color = Gray)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Parte 1
                Row(
                    modifier = Modifier
                        .weight(1.5f)
                        .fillMaxWidth()
                        .padding(start = 80.dp, end = 80.dp), // mismo padding lateral
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Columna izquierda: Etiqueta (alineada a la izquierda)
                    Column(
                        modifier = Modifier.weight(1f), // espacio proporcional
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Debes",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = OpenSansSemiCondensed,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }

                    // Columna derecha: Cantidad (alineada a la derecha)
                    Column(
                        modifier = Modifier.weight(1f), // mismo peso
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "- 0,00 €",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = OpenSauce,
                                color = Color.Red
                            )
                        )
                    }
                }
                HorizontalDivider(color = Gray, thickness = 1.dp)

                // Parte 2
                Row(
                    modifier = Modifier
                        .weight(1.5f)
                        .fillMaxWidth()
                        .padding(start = 80.dp, end = 80.dp), // mismo padding lateral
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Columna izquierda: Etiqueta (alineada a la izquierda)
                    Column(
                        modifier = Modifier.weight(1f), // espacio proporcional
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Te deben",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = OpenSansSemiCondensed,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }

                    // Columna derecha: Cantidad (alineada a la derecha)
                    Column(
                        modifier = Modifier.weight(1f), // mismo peso
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "+ 0,00 €",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = OpenSauce,
                                color = Green
                            )
                        )
                    }
                }

                HorizontalDivider(color = Gray, thickness = 1.dp)

                // Parte 3
                Row(
                    modifier = Modifier
                        .weight(1.5f)
                        .fillMaxWidth()
                        .padding(start = 80.dp, end = 80.dp), // mismo padding lateral
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Columna izquierda: Etiqueta (alineada a la izquierda)
                    Column(
                        modifier = Modifier.weight(1f), // espacio proporcional
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Saldo real",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = OpenSansSemiCondensed,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }

                    // Columna derecha: Cantidad
                    Column(
                    ) {
                        Text(
                            text = "0,00 €",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = OpenSauce,
                                color = Color.Black
                            )
                        )
                    }
                }
            }
        }

        //BOX DE INGRESO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .border(width = 1.dp, color = Gray)
                .background(Color.White)
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Texto superior
                Text(
                    text = "Ingresar dinero",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = OpenSansSemiCondensed,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                // Botones de cantidad rápida
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    listOf("10", "20", "50").forEach { amount ->
                        Button(
                            onClick = {
                                customAmount = amount
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                            ),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp) // sin sombra
                        ) {
                            Text(
                                text = "$amount €",
                                style = TextStyle(
                                    fontFamily = OpenSauce,
                                    fontSize = 18.sp,
                                    color = BlueLight
                                )
                            )
                        }

                    }
                }

                // Tu CustomTextField
                NumericTextField(
                    label = null,
                    placeholder = "0,00",
                    value = customAmount,
                    onValueChange = { customAmount = it },
                    modifier = Modifier.fillMaxWidth(0.4f) // o null si no quieres aplicar ninguno
                )

                // Botón final
                IngresarButtonComponent(
                    onIngresarClick = {
                        // Validación del monto ingresado
                        customAmount.toDoubleOrNull()?.let { amount ->
                            // 1. Guardamos el monto en el ViewModel
                            walletViewModel.setPendingAmount(amount)

                            // 2. Lanzamos Google Pay
                            paymentRepository.launchGooglePay(
                                context = context,
                                amount = customAmount,
                                activity = activity
                            )
                        }
                    }
                )
                // Toast confirmación ✅
                if (recargaExitosa == true) {
                    LaunchedEffect(recargaExitosa) {
                        Toast.makeText(
                            context,
                            "Ingreso realizado correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        walletViewModel.resetearRecarga()
                    }
                }
            }
        }
    }
}

fun formatPrice1(amount: Double): String {
    return String.format("%.2f €", amount)
}



