package com.example.ptdapp.ui.navigation


import android.app.Activity
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ptdapp.ui.screens.createGastoScreen.CreateGastoScreen
import com.example.ptdapp.ui.screens.createPTDScreen.CreatePTDScreen
import com.example.ptdapp.ui.screens.detailGastoScreen.DetailGastoScreen
import com.example.ptdapp.ui.screens.detailPTDScreen.DetailPTDScreen
import com.example.ptdapp.ui.screens.homeScreen.HomeScreen
import com.example.ptdapp.ui.screens.loginScreen.LoginScreen
import com.example.ptdapp.ui.screens.mainScreen.MainScreen
import com.example.ptdapp.ui.screens.notificationScreen.NotificationScreen
import com.example.ptdapp.ui.screens.profileScreen.ProfileScreen
import com.example.ptdapp.ui.screens.registerScreen.RegisterScreen
import com.example.ptdapp.ui.screens.walletScreen.WalletScreen
import com.example.ptdapp.ui.authViewmodel.AuthViewModel
import com.example.ptdapp.ui.authViewmodel.AuthViewModelFactory
import com.example.ptdapp.ui.screens.walletScreen.WalletViewModel

@Composable
fun NavGraph(navController: NavHostController,walletViewModel: WalletViewModel) {
    val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory()) // ✅ Usa Factory
    val user by authViewModel.user.collectAsState()

    val startDestination = if (user != null) Destinations.MAIN_SCREEN else Destinations.LOGIN_SCREEN

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.LOGIN_SCREEN) { LoginScreen(navController, authViewModel) }
        composable(Destinations.REGISTER_SCREEN) { RegisterScreen(navController, authViewModel) }
        composable(Destinations.MAIN_SCREEN) { MainScreen(navController, walletViewModel) }
        composable(Destinations.CREATE_GASTO_SCREEN) { CreateGastoScreen(navController) }
        composable(Destinations.CREATE_PTD_SCREEN) { CreatePTDScreen(navController) }
        composable(Destinations.DETAIL_GASTO_SCREEN) { DetailGastoScreen(navController) }
        composable(Destinations.DETAIL_PTD_SCREEN) { DetailPTDScreen(navController) }
        composable(Destinations.HOME_SCREEN) { HomeScreen(navController) }
        composable(Destinations.NOTIFICATION_SCREEN) { NotificationScreen(navController) }
        composable(Destinations.PROFILE_SCREEN) { ProfileScreen(navController, authViewModel) }
        composable(Destinations.WALLET_SCREEN) { WalletScreen(navController, walletViewModel) }


    }

    // ✅ Redirige automáticamente a login si el usuario cierra sesión
    LaunchedEffect(user) {
        if (user == null) {
            navController.navigate(Destinations.LOGIN_SCREEN) {
                popUpTo(Destinations.LOGIN_SCREEN) { inclusive = true } // ✅ Limpia la pila de navegación
            }
        }
    }
}

