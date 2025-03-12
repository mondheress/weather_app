package com.creative.weather_app.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.creative.weather_app.ui.screen.FavoriteScreen.FavoriteScreen
import com.creative.weather_app.ui.screen.about.AboutScreen
import com.creative.weather_app.ui.screen.main.MainScreen
import com.creative.weather_app.ui.screen.main.MainScreenViewModel
import com.creative.weather_app.ui.screen.setting.SettingScreen
import com.creative.weather_app.ui.screen.splash.SplashScreen
import com.creative.weather_app.ui.screen.sreach.SearchScreen

@Composable
fun WeatherNavigation() {

    val route = "MainScreen"
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreen.SplashScreen.name) {
        composable(WeatherScreen.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(
            "$route/{city}", arguments = listOf(navArgument(name = "city")
            {
                type = NavType.StringType
            })
        )
        { navBack ->
            navBack.arguments?.getString("city").let { city ->
                val mainViewModel = hiltViewModel<MainScreenViewModel>()
                MainScreen(navController = navController, mainViewModel, country = city)
            }

        }
        composable(WeatherScreen.SearchScreen.name) {
            val mainViewModel = hiltViewModel<MainScreenViewModel>()
            SearchScreen(nav = navController, mainViewModel)
        }

        composable(WeatherScreen.FavoriteScreen.name) {
            FavoriteScreen(nav = navController)
        }

        composable(WeatherScreen.About.name) {
            AboutScreen(nav = navController)
        }

        composable(WeatherScreen.SettingScreen.name) {
            SettingScreen(nav = navController)
        }
    }
}