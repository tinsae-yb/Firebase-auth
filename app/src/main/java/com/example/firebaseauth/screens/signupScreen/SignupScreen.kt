package com.example.firebaseauth.screens.signupScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.firebaseauth.service.AuthService
import com.example.firebaseauth.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    navController: NavController,



    vm: SignupViewModel = viewModel(factory = SignupViewModel.Factory)



) {

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {

            TextField(modifier = Modifier.fillMaxWidth(),

                value = vm.email.value, onValueChange = {
                    vm.email.value = it
                },

                label = {
                    Text(text = "Email")
                })



            Spacer(modifier = Modifier.height(10.dp))

            TextField(modifier = Modifier.fillMaxWidth(),

                value = vm.password.value, onValueChange = {
                    vm.password.value = it
                },

                label = {
                    Text(text = "Password")
                })


            Spacer(modifier = Modifier.height(10.dp))

            TextButton(onClick = {

                navController.navigate(Screen.LoginScreen.route) {
                    popUpTo(Screen.SignUpScreen.route) {
                        inclusive = true
                    }
                }

            }) {

                Text(text = "Already have an account? Login")
            }
            Button(


                onClick = { /*TODO*/

                    vm.signup()

                }) {

                Text(text = "Signup")
            }


        }


    }


}

@Composable
@Preview
fun PreviewSignupScreen() {
    SignupScreen(
        navController = NavController(LocalContext.current), vm = SignupViewModel(
            AuthService()
        )
    )
}