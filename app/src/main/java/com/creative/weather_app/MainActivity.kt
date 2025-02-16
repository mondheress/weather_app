package com.creative.weather_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.creative.weather_app.navigation.WeatherNavigation
import com.creative.weather_app.ui.theme.Weather_appTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            weatherApp()
        }
    }
}


@Composable
fun weatherApp() {

    Weather_appTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){  }
            WeatherNavigation()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Weather_appTheme {
        weatherApp()
    }
}