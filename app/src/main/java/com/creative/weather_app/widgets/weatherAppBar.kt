package com.creative.weather_app.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.creative.weather_app.navigation.WeatherScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    tile: String,
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {

    val showDialog = remember {
        mutableStateOf(false)
    }
    val showIt = remember {
        mutableStateOf(false)
    }
    if (showDialog.value) {
        ShowDropDown(showDialog = showDialog, navController = navController)
    }

    TopAppBar(
        title = { Text(text = tile) },
        modifier = Modifier.shadow(elevation = elevation),
        actions = {
            if (isMainScreen) {
                IconButton(onClick = { onAddActionClicked.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search icon",
                    )
                }

                IconButton(onClick = { showDialog.value = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More icon",
                    )
                }
            } else {
                Box(modifier = Modifier.padding(0.dp)) { }
            }


        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    })
            }


        },
        colors = topAppBarColors(Color.White)
    )


}

@Composable
fun ShowDropDown(showDialog: MutableState<Boolean>, navController: NavController) {
    var expanded by remember {
        mutableStateOf(true)
    }
    val items = listOf("About", "Favorites", "Settings")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    )
    {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                showDialog.value = false},
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    text = {
                        Row {
                            Icon(
                                imageVector = when (s) {
                                    "About" -> Icons.Default.Info
                                    "Favorites" -> Icons.Default.Favorite
                                    else -> Icons.Default.Settings
                                },
                                contentDescription = null,
                                tint = Color.Gray
                            )
                            Text(
                                text = s,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 8.dp).clickable {
                                    navController.navigate(
                                    when (s) {
                                        "About" -> WeatherScreen.About.name
                                        "Favorites" -> WeatherScreen.FavoriteScreen.name
                                        else -> WeatherScreen.SettingScreen.name
                                    })
                                }
                            )
                        }
                    },
                    onClick = {
                        expanded = false
                        showDialog.value = false

                    }
                )
            }
        }
    }
}