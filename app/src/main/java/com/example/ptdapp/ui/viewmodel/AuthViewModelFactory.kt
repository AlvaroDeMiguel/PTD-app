package com.example.ptdapp.ui.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ptdapp.data.repositories.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class AuthViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(AuthRepository(FirebaseAuth.getInstance())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

