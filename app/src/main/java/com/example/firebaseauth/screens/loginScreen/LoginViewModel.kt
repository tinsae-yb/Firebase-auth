package com.example.firebaseauth.screens.loginScreen

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.firebaseauth.service.AuthService
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInCredential
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

const val TAG = "SignupViewModel"

class LoginViewModel(private val authService: AuthService) : ViewModel() {
    fun login() {
        viewModelScope.launch {
            val authResult = try {
                authService.login(email.value, password.value)
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                null
            }

        }

    }


    suspend fun beginSignInWithGoogle(context: Context): BeginSignInResult? {
        return authService.beginSignInWithGoogle(context)
    }

    fun credentialFromIntent(context: Context, data: Intent?): SignInCredential {
        return authService.credentialFromIntent(context, data)
    }

    fun signInWithCredential(credential: SignInCredential) {
        viewModelScope.launch {
            val authResult = try {
                authService.signInWithCredential(credential)
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
                LoginViewModel(authService)
            }
        }
    }

}