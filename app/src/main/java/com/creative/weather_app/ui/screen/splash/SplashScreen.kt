package com.creative.weather_app.ui.screen.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.creative.weather_app.R
import com.creative.weather_app.navigation.WeatherScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 0.9f, animationSpec = tween(durationMillis = 800, delayMillis = 200,
                easing = { OvershootInterpolator(8f).getInterpolation(it) })
        )
        delay(2000L)
        navController.navigate(WeatherScreen.MainScreen.name)
    })
    Box(
        modifier = Modifier.size(230.dp), // Constrain the size here
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .padding(15.dp)
                .size(230.dp)
                .scale(scale.value),
            shape = CircleShape,
            color = Color.White,
            border = BorderStroke(2.dp, Color.LightGray),
        )
        {
            Column(
                modifier = Modifier.padding(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.sun_icon),
                    contentDescription = "sun icon",
                    contentScale = ContentScale.Fit
                )
                Text(text = "Find the Sun ?")


            }


        }

    }
}