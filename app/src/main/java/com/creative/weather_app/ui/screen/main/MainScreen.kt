package com.creative.weather_app.ui.screen.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.creative.weather_app.data.DataofException
import com.creative.weather_app.model.weatherResponse
import com.creative.weather_app.widgets.weatherAppBar

@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainScreenViewModel) {


    get_Weather(mainViewModel, navController)

}

@Composable
private fun get_Weather(mainViewModel: MainScreenViewModel, navController: NavHostController) {
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
        MainScaffold(navController, weatherData.data!!)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(mainViewModel: NavHostController, data: weatherResponse) {
    Scaffold(topBar = {
        weatherAppBar(
            data.city.name + "," + data.city.country,
            elevation = 5.dp,
            navController = mainViewModel,
            icon = Icons.Default.ArrowBack
        )
        {
            Log.d("mondher","buttonClicked")
        }
    })
    {
        MainContent(data)

    }

}

@Composable
fun MainContent(data: weatherResponse) {
    Text(text = data.city.name)

}
