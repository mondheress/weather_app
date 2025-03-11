package com.creative.weather_app.navigation

enum class WeatherScreen {
    SplashScreen,
    MainScreen,
    About,
    FavoriteScreen,
    SearchScreen,
    SettingScreen;

    companion object {
        fun fromRoute(route: String?): WeatherScreen = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            MainScreen.name -> MainScreen
            SearchScreen.name -> SearchScreen
            null -> SplashScreen
            else -> throw IllegalArgumentException("Route $route is not recognized.")
        }
    }}
