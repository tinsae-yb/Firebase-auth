package com.example.firebaseauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauth.ui.theme.FirebaseAuthTheme
import com.example.firebaseauth.util.AuthStatus
import com.example.firebaseauth.util.Navigation
import com.example.firebaseauth.util.Screen

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel: MainViewModel by viewModels(factoryProducer = { MainViewModel.Factory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel.listenToAuthStateChanges { it ->

            if (it == AuthStatus.AUTHENTICATED) {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(navController.currentDestination?.route?:Screen.SignUpScreen.route) {
                        inclusive = true
                    }
                }

            } else if (it == AuthStatus.UNAUTHENTICATED) {

                navController.currentDestination?.route?.let {
                    if(it == Screen.SplashScreen.route){
                        navController.navigate(Screen.SignUpScreen.route) {
                            popUpTo(Screen.SplashScreen.route) {
                                inclusive = true
                            }
                        }
                    }else{
                        navController.navigate(Screen.SignUpScreen.route) {
                            popUpTo(Screen.HomeScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }


        setContent {
            navController = rememberNavController()
            FirebaseAuthTheme {

                Navigation(navController)

            }
        }
    }


}