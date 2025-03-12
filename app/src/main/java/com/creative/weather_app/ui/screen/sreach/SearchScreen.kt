package com.creative.weather_app.ui.screen.sreach

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.creative.weather_app.navigation.WeatherScreen
import com.creative.weather_app.ui.screen.main.MainScreenViewModel
import com.creative.weather_app.utils.Constants.Constants.route
import com.creative.weather_app.widgets.WeatherAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(nav: NavHostController, mainViewModel: MainScreenViewModel) {

    Scaffold(topBar = {
        WeatherAppBar(
            tile = "SearchScreen", icon = Icons.Default.ArrowBack,
            isMainScreen = false, elevation = 5.dp,
            navController = nav,
        ) {
            nav.navigate("$route/") // Correct navigation
        }
    },
        contentWindowInsets = WindowInsets(0, 0, 0, 0))
    { innerPadding ->
        Log.d("mondherSearch", "here")
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) { // Apply the inner padding
            Column(modifier = Modifier.padding(16.dp).fillMaxSize(),
                //Center Colum inside screen
                // verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally

            ) { // Add some padding to the Column
                SearchBar(modifier = Modifier.fillMaxWidth().padding(16.dp).
                align(Alignment.CenterHorizontally).
                height(90.dp)) { it->
                    Log.d("mondher","SearchContent: "+it)
                  //  nav.navigate(WeatherScreen.MainScreen.name/${it}") // Correct navigation
                    nav.navigate("${WeatherScreen.MainScreen.name}/$it") // Correct navigation

                }
            }
        }
    }
}


@Composable
fun SearchBar (modifier: Modifier,onSearch: (String) -> Unit = {})
{
    val route = "MainScreen"
    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = rememberSaveable(searchQueryState) {
        searchQueryState.value.trim().isNotEmpty()
    }
    Column (modifier = modifier){
        CommonTextField(
            valueState = searchQueryState,
            placeholder = "Tunis",
            onAction = KeyboardActions {
               // if (!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()

            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(valueState: MutableState<String>, placeholder: String,
                      keyboardType: KeyboardType = KeyboardType.Text,
                      imeAction: ImeAction = ImeAction.Next,
                      onAction: KeyboardActions = KeyboardActions.Default) {

    OutlinedTextField(value = valueState.value,
        onValueChange = { valueState.value = it},
        label = { Text(text = placeholder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = androidx.compose.ui.graphics.Color.Blue,
            cursorColor = androidx.compose.ui.graphics.Color.Black),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxSize().padding(start = 10.dp, end = 10.dp)
        )

}