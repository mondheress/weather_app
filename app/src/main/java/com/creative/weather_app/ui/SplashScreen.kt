package com.creative.weather_app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun SplashScreen(navController: NavHostController) {
    Text(text = "Splash Screen", modifier = Modifier.fillMaxSize())

}