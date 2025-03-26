package com.example.ptdapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.ptdapp.ui.navigation.NavGraph
import com.example.ptdapp.ui.authViewmodel.AuthViewModel
import com.example.ptdapp.ui.theme.PTDAppTheme
import androidx.compose.runtime.collectAsState
import com.example.ptdapp.ui.authViewmodel.AuthViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory()) // ✅ Se usa Factory
            val userState = viewModel.user.collectAsState()
            val user = userState.value

            PTDAppTheme {
                NavGraph(navController)
            }
        }
    }
}



