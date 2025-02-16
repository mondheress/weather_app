package com.creative.weather_app.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.creative.weather_app.ui.screen.main.MainScreen
import com.creative.weather_app.ui.screen.main.MainScreenViewModel
import com.creative.weather_app.ui.screen.splash.SplashScreen


@Composable
fun WeatherNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreen.SplashScreen.name) {
        composable(WeatherScreen.SplashScreen.name) {

            SplashScreen(navController = navController)
        }
        composable(WeatherScreen.MainScreen.name) {
            val mainViewModel = hiltViewModel<MainScreenViewModel>()
            MainScreen(navController = navController,mainViewModel)
        }
    }
}