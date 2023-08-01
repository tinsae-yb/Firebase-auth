package com.example.firebaseauth.util

sealed class    Screen (val route:String){

    object LoginScreen:Screen("login_screen")
    object SignUpScreen:Screen("sign_up_screen")
    object SplashScreen:Screen("splash_screen")
    object HomeScreen:Screen("home_screen")


}