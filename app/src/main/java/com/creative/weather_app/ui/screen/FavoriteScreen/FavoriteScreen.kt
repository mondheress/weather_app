package com.creative.weather_app.ui.screen.FavoriteScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.creative.weather_app.model.Favorite
import com.creative.weather_app.utils.Constants.Constants.route
import com.creative.weather_app.widgets.WeatherAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(nav: NavHostController, favoriteViewModel: FavoriteViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                tile = "FavoriteScreen", icon = Icons.Default.ArrowBack,
                isMainScreen = false, elevation = 5.dp,
                navController = nav,
            ) {
                nav.navigate("$route/") // Correct navigation
            }
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    )
    {
        Surface(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val list = favoriteViewModel.favList.collectAsState().value
                LazyColumn {

                    items(items = list)
                    {
                        CityRow(
                            it, navController = nav, favoriteViewModel
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun CityRow(it: Favorite, navController: NavHostController, favoriteViewModel: FavoriteViewModel) {

    Surface(
        Modifier
            .padding(5.dp)
            .fillMaxSize()
            .height(50.dp)
            .clickable {
                navController.navigate("$route/${it.city}")
            }, shape = RoundedCornerShape(15.dp),
        color = Color(0xFFB2DFDB)
    )

    {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Text(text = it.city, modifier = Modifier.padding(start = 25.dp))
            Surface(shape = CircleShape, color = Color(0xFFD1E3E1))

            {
                Text(
                    text = it.country,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Black
                )
            }

            Icon(
                imageVector = Icons.Rounded.Delete, contentDescription = "Delete",
                modifier = Modifier.clickable {
                    favoriteViewModel.deleteFavorite(it)

                },
                tint = Color.Red.copy(alpha = 0.3f)
            )
        }

    }
}
