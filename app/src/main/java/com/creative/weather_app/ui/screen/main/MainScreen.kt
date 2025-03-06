package com.creative.weather_app.ui.screen.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
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
            Log.d("mondher", "buttonClicked")
        }
    })
    {
        MainContent(data)

    }

}

@Composable
fun MainContent(data: weatherResponse) {
    val imageUrl = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"
    Log.d("mondher:", "image: $imageUrl")

    Column(
        Modifier
            .padding(0.dp, 100.dp, 0.dp)
            .fillMaxWidth(),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Jeudi 6 Mars 2025",
            modifier = Modifier.padding(0.dp, 50.dp),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold
        )
        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        )
        {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                WeatherStateImage(imageUrl = imageUrl)
                Text(text = "54", fontWeight = FontWeight.ExtraBold)
                Text(text = "Rain", fontStyle = FontStyle.Italic)

            }
        }

    }
    Text(text = data.city.name)

}

@Composable
fun WeatherStateImage(imageUrl: String) {


    AsyncImage(model = imageUrl, contentDescription = "Icone Image2", modifier = Modifier.size(50.dp))
}
