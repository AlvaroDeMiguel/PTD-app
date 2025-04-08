package com.example.ptdapp.ui.screens.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ptdapp.R
import com.example.ptdapp.ui.navigation.Destinations
import com.example.ptdapp.ui.screens.homeScreen.HomeScreen
import com.example.ptdapp.ui.screens.notificationScreen.NotificationScreen
import com.example.ptdapp.ui.screens.walletScreen.WalletScreen
import com.example.ptdapp.ui.screens.walletScreen.WalletViewModel
import com.example.ptdapp.ui.theme.BlueLight
import com.example.ptdapp.ui.theme.Gray
import com.example.ptdapp.ui.theme.LightBlue
import com.example.ptdapp.ui.theme.LightGray
import com.example.ptdapp.ui.theme.OpenSansNormal
import com.example.ptdapp.ui.theme.OpenSansSemiCondensed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, walletViewModel: WalletViewModel) {
    val items = listOf(BottomNavItem.Wallet, BottomNavItem.Home, BottomNavItem.Notifications)
    var selectedItem by remember { mutableStateOf(BottomNavItem.Home.route) } // Guardar la ruta en String

    Scaffold(
        topBar = {
            Column {
                val currentItem = items.find { it.route == selectedItem }
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp), // Margen lateral opcional
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = currentItem?.icon ?: R.drawable.home
                                ),
                                contentDescription = currentItem?.title,
                                modifier = Modifier.size(42.dp),
                                tint = Gray
                            )
                            Spacer(modifier = Modifier.padding(start = 30.dp)) // Espacio entre icono y texto
                            Text(
                                text = currentItem?.title ?: "App",
                                style = TextStyle(
                                    fontFamily = OpenSansNormal,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp,
                                    color = Color.Black
                                )
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White
                    ),
                    actions = {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { navController.navigate(Destinations.PROFILE_SCREEN) }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.account_circle),
                                    contentDescription = "Perfil",
                                    modifier = Modifier.size(42.dp),
                                    tint = BlueLight
                                )
                            }
                        }
                    }
                )
                HorizontalDivider(color = Gray, thickness = 1.dp) // Línea divisoria marcada
            }
        },
        bottomBar = {
            Column {
                HorizontalDivider(color = Gray, thickness = 1.dp) // Línea divisoria marcada
                NavigationBar(
                    containerColor = Color.White
                ) {
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = selectedItem == item.route, // Comparar con la ruta
                            onClick = {
                                selectedItem = item.route
                            }, // Guardar la ruta al seleccionar
                            icon = {
                                Box(contentAlignment = Alignment.Center) {
                                    if (selectedItem == item.route) {
                                        Box(
                                            modifier = Modifier
                                                .size(55.dp) // Tamaño más pequeño del círculo
                                                .background(LightBlue, shape = CircleShape)
                                        )
                                    }
                                    Icon(
                                        painter = painterResource(id = item.icon),
                                        contentDescription = item.title,
                                        modifier = Modifier.size(38.dp), // Tamaño del icono
                                        tint = Gray // Cambio de color cuando está seleccionado
                                    )
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent // Se mantiene transparente ya que manejamos el indicador manualmente
                            )
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, // Centra el icono y el texto
                modifier = Modifier.padding(10.dp)
            ) {
                IconButton(
                    onClick = { navController.navigate(Destinations.CREATE_PTD_SCREEN) },
                    modifier = Modifier
                        .size(60.dp) // Ajusta el tamaño del icono
                        .background(Color.Transparent) // Fondo totalmente transparente
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_circle),
                        contentDescription = "Añadir",
                        modifier = Modifier.size(60.dp), // Ajusta al tamaño necesario
                        tint = BlueLight
                    )
                }


                Text(
                    text = "Crear PTD",
                    style = TextStyle(
                        fontFamily = OpenSansSemiCondensed,
                        fontSize = 11.sp,
                        color = BlueLight
                    )
                )
            }
        }

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(LightGray)
        ) {
            when (selectedItem) {
                BottomNavItem.Wallet.route -> WalletScreen(navController, walletViewModel)
                BottomNavItem.Home.route -> HomeScreen(navController)
                BottomNavItem.Notifications.route -> NotificationScreen(navController)
            }
        }
    }
}


sealed class BottomNavItem(val route: String, val title: String, val icon: Int) {
    object Wallet : BottomNavItem("wallet", "Cartera", R.drawable.wallet)
    object Home : BottomNavItem("home", "Inicio", R.drawable.home)
    object Notifications :
        BottomNavItem("notifications", "Notificaciones", R.drawable.notifications)
}
