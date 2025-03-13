package com.creative.weather_app.ui.screen.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.creative.weather_app.data.DataofException
import com.creative.weather_app.model.weatherResponse
import com.creative.weather_app.navigation.WeatherScreen
import com.creative.weather_app.utils.DateUtils.Companion.convertTimeStampToDate
import com.creative.weather_app.widgets.WeatherAppBar
import com.creative.weather_app.widgets.WeatherDetailsItem
import com.creative.weather_app.widgets.WeatherPressureRow
import com.creative.weather_app.widgets.WeatherSecondRow
import com.creative.weather_app.widgets.WeatherStateImage

@Composable
fun MainScreen(
    navController: NavHostController,
    mainViewModel: MainScreenViewModel,
    country: String?
) {

    val DefaultCity = "Tunis"

    Log.d("CurrentDefaultCity", "Cityis1: +"+country.toString())

    if (country.isNullOrEmpty()) {
        Get_Weather(mainViewModel, navController,DefaultCity)
    }
    else{
        Get_Weather(mainViewModel, navController,country)

    }
}

@Composable
private fun Get_Weather(mainViewModel: MainScreenViewModel, navController: NavHostController,city:String) {
   val weatherData = produceState<DataofException<weatherResponse, Boolean, Exception>>(
        initialValue = DataofException(loading = true),
        key1 = city // Provide the city as a key

   ) {
        value = mainViewModel.getWeatherDatav2(city.toString())
    }.value

    //20164418

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
fun MainScaffold(navController: NavHostController, data: weatherResponse) {

    Scaffold(topBar = {
        WeatherAppBar(
            data.city.name + "," + data.city.country,
            elevation = 5.dp,
            navController = navController,
            icon = Icons.Default.ArrowBack,
            isMainScreen = true,
            onAddActionClicked = {
                Log.d("mondher", "buttonClicked2")
                navController.navigate(WeatherScreen.SearchScreen.name)
            }
        )
        {
            Log.d("mondher", "buttonClicked")
        }
    },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    )
    { innerPadding->
        MainContent(data,innerPadding)

    }
}

@Composable
fun MainContent(data: weatherResponse,innerpadding: PaddingValues) {
    val imageUrl = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"
    Log.d("mondher:", "image: $imageUrl")

    Column(
        Modifier
            .padding(innerpadding)
            .fillMaxWidth(),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally //Center Colum inside screen
    ) {

        Text(
            text = convertTimeStampToDate(data.list[0].dt.toLong()),
            modifier = Modifier.padding(0.dp, 20.dp),
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
                Text(text = data.list[0].temp.day.toString(), fontWeight = FontWeight.ExtraBold)
                Text(text = data.list[0].weather[0].main, fontStyle = FontStyle.Italic)


            }

        }
        WeatherPressureRow(data.list)
        HorizontalDivider()
        WeatherSecondRow(data.list)
        Text(text = "This week", fontWeight = FontWeight.Bold)

        WeatherDetails(data.list)

    }
    Text(text = data.city.name)

}

@Composable
fun WeatherDetails(data: List<weatherResponse.Item0>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEEF1FF),
        shape = RoundedCornerShape(size = 10.dp)
    ) {

        LazyColumn(modifier = Modifier.padding(3.dp), contentPadding = PaddingValues(1.dp)) {
            items(data.size) { index ->
                val da = data[index]
                WeatherDetailsItem(da)// ✅ Corrected reference

            }

        }


    }

}


