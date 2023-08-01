package com.example.firebaseauth.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.firebaseauth.service.AuthService

class HomeViewModel  (private val authService: AuthService) :ViewModel() {
    fun signOut() {
        authService.signOut()
    }


    companion object
{
    val Factory : ViewModelProvider.Factory = viewModelFactory {
        initializer {

            HomeViewModel(AuthService())
        }
    }
}

}