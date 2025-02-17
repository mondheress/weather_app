package com.creative.weather_app.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.creative.weather_app.data.DataofException
import com.creative.weather_app.model.weatherResponse

@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainScreenViewModel) {


    get_Weather(mainViewModel)

}

@Composable
private fun get_Weather(mainViewModel: MainScreenViewModel) {
    val weatherData = produceState<DataofException<weatherResponse, Boolean, Exception>>(
        initialValue = DataofException(loading = true)
    ) {
        value = mainViewModel.getWeatherDatav2("tunis")
    }.value
    if (weatherData.loading == true) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (weatherData.data != null) {
        Text(text = "Main Screen ${weatherData.data.toString()}")

    }
}