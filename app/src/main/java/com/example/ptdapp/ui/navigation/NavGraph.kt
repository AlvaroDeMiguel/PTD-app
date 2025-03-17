package com.example.ptdapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
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

@Composable
fun NavGraph(navController: NavHostController, isUserLoggedIn: Boolean) {
    NavHost(
        navController = navController,
        startDestination = if (isUserLoggedIn) Destinations.MAIN_SCREEN else Destinations.LOGIN_SCREEN    )
    {
        composable(Destinations.LOGIN_SCREEN) { LoginScreen(navController) }
        composable(Destinations.REGISTER_SCREEN) { RegisterScreen(navController) }
        composable(Destinations.MAIN_SCREEN) { MainScreen(navController) }
        composable(Destinations.CREATE_GASTO_SCREEN) { CreateGastoScreen(navController) }
        composable(Destinations.CREATE_PTD_SCREEN) { CreatePTDScreen(navController) }
        composable(Destinations.DETAIL_GASTO_SCREEN) { DetailGastoScreen(navController) }
        composable(Destinations.DETAIL_PTD_SCREEN) { DetailPTDScreen(navController) }
        composable(Destinations.HOME_SCREEN) { HomeScreen(navController) }
        composable(Destinations.NOTIFICATION_SCREEN) { NotificationScreen(navController) }
        composable(Destinations.PROFILE_SCREEN) { ProfileScreen(navController) }
        composable(Destinations.WALLET_SCREEN) { WalletScreen(navController) }
    }
}