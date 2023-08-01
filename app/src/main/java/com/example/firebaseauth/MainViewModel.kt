package com.example.firebaseauth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory.Companion
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.firebaseauth.service.AuthService
import com.example.firebaseauth.util.AuthStatus

class MainViewModel(private val authService: AuthService) : ViewModel() {
    fun listenToAuthStateChanges(listener: (AuthStatus) -> Unit) {
        authService.listenToAuthStateChanges {
            it.currentUser?.let {
                listener(AuthStatus.AUTHENTICATED)
            } ?: run {
                listener(AuthStatus.UNAUTHENTICATED)
            }
        }

    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val authService = AuthService()
                MainViewModel(authService)
            }
        }
    }


}