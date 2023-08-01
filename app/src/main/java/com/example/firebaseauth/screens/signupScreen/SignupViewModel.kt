package com.example.firebaseauth.screens.signupScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.firebaseauth.service.AuthService
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

const val TAG = "SignupViewModel"

class SignupViewModel(private val authService: AuthService) : ViewModel() {
    fun signup() {
        viewModelScope.launch {
            val authResult = try {
                authService.signUp(email.value, password.value)
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                null
            }

        }

    }


    val email = mutableStateOf("")
    val password = mutableStateOf("")

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val authService = AuthService()
                SignupViewModel(authService)
            }
        }
    }

}