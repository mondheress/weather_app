package com.creative.weather_app.ui.screen.about

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.creative.weather_app.utils.Constants.Constants.route
import com.creative.weather_app.widgets.WeatherAppBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutScreen(nav: NavHostController) {
    Scaffold(
        topBar  = {
            WeatherAppBar(
                tile = "About Screen", icon = Icons.Default.ArrowBack,
                isMainScreen = false, elevation = 5.dp,
                navController = nav,
            )
            {
                nav.navigate("$route/") // Correct navigation
            }
        }
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(text = "About this App")
            Text(
                text = "Check this link",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }


}