package com.example.firebaseauth.screens.loginScreen

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.firebaseauth.util.Screen
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController, vm: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
) {


    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->

            Log.d(TAG, "LoginScreen:  ${result.resultCode} ")
            if(result.resultCode == Activity.RESULT_OK){
                val intent = result.data
                val credential = vm.credentialFromIntent(context, intent)

                try {
                    vm.signInWithCredential(credential)
                }catch (e: Exception){
                    Log.d(TAG, "LoginScreen: ${e.message}")
                }

            }
            else{
                Log.d(TAG, "LoginScreen: ${result.data?.getStringExtra("error")}")
            }

        }







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
//            Sign in with google button
            Button(onClick = {


                coroutineScope.launch {

                    try {
                        val signInResult = vm.beginSignInWithGoogle(context)
                        if (signInResult != null) {
                            val intent =
                                IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender)
                                    .build()
                            launcher.launch(intent)
                        }

                    } catch (e: Exception) {

                        Log.d(TAG, "LoginScreen: ${e.message}")
                        if (e is CancellationException) throw e
                        null
                    }

                }


            }) {

                Text(text = "Sign in with Google")
            }
            Spacer(modifier = Modifier.height(10.dp))

            TextButton(onClick = {

                navController.navigate(Screen.SignUpScreen.route) {
                    popUpTo(Screen.LoginScreen.route) {
                        inclusive = true
                    }
                }

            }) {

                Text(text = "Don't have an account? Register")
            }
            Button(


                onClick = {

                    vm.login()

                }) {

                Text(text = "Login")
            }


        }


    }

}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = NavController(LocalContext.current))
}