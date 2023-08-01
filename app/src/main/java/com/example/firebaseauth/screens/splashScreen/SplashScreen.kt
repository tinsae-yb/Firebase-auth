package com.example.firebaseauth.screens.splashScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.firebaseauth.R
import com.example.firebaseauth.util.Screen
import kotlinx.coroutines.delay
import kotlin.math.log


@Composable
fun SplashScreen(
    navController: NavController,

) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
    val progress by animateLottieCompositionAsState(
        composition = composition, iterations = LottieConstants.IterateForever

    )


    Surface(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.secondary,

        ) {

        LottieAnimation(
            composition = composition,
            progress = { progress },
        )
    }


}