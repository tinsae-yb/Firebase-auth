package com.example.firebaseauth.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauth.screens.homeScreen.HomeScreen
import com.example.firebaseauth.screens.loginScreen.LoginScreen
import com.example.firebaseauth.screens.signupScreen.SignupScreen
import com.example.firebaseauth.screens.splashScreen.SplashScreen


@Composable
fun Navigation(navController: NavHostController) {

//    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = Screen.SplashScreen.route
    ) {

        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SignUpScreen.route) {
            SignupScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route) {
         HomeScreen(navController = navController)
        }
    }


}