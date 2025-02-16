package com.creative.weather_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.creative.weather_app.ui.screen.main.MainScreen
import com.creative.weather_app.ui.screen.splash.SplashScreen


@Composable
fun WeatherNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreen.SplashScreen.name) {
        composable(WeatherScreen.SplashScreen.name) {

            SplashScreen(navController = navController)
        }
        composable(WeatherScreen.MainScreen.name) {

            MainScreen(navController = navController)
        }
        // Add other composable destinations here...
    }
}