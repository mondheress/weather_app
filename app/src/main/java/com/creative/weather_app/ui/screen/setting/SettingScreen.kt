package com.creative.weather_app.ui.screen.setting

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.creative.weather_app.model.Setting_Unit
import com.creative.weather_app.widgets.WeatherAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen (nav: NavHostController, settingsViewModel: SettingsViewModel = hiltViewModel()) {
   var unitToggleState by remember { mutableStateOf(false) }
   val measurementUnit = listOf("Imperial (F)", "Metric (C)")
   val choiceFromDb = settingsViewModel.settingList.collectAsState().value

   val defaultChoice = if (choiceFromDb.isEmpty()) measurementUnit[0]
   else choiceFromDb[0].unit

   var choiceState by remember {
      mutableStateOf(defaultChoice)
   }

   Scaffold(topBar = {
      WeatherAppBar(
         tile = "Settings",
         icon = Icons.Default.ArrowBack,
         false,
         navController = nav){
         nav.popBackStack()
      }
   }) {
      Surface(
         modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
      ) {
         Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
         ) {
            Text(
               text = "Change Units of Measurement",
               modifier = Modifier.padding(bottom = 15.dp)
            )

            IconToggleButton(
               checked = !unitToggleState,
               onCheckedChange = {
                  unitToggleState = !it
                  choiceState = if (unitToggleState) {
                     "Imperial (F)"
                  } else {
                     "Metric (C)"
                  }
                  Log.d("TAG", "MainContent: $unitToggleState")

               }, modifier = Modifier
                  .fillMaxWidth(0.5f)
                  .clip(shape = RectangleShape)
                  .padding(5.dp)
                  .background(Color.Magenta.copy(alpha = 0.4f))
            ) {

               Text(text = if (unitToggleState) "Fahrenheit ºF" else "Celsius ºC")


            }
            Button(
               onClick = {
                  settingsViewModel.deleteUnit()
                  settingsViewModel.insertUnit(Setting_Unit(unit = choiceState))
                  Log.d("mondher","CurrentChoice: "+choiceState)

               },
               modifier = Modifier
                  .padding(3.dp)
                  .align(CenterHorizontally),
               shape = RoundedCornerShape(34.dp),
               colors = ButtonDefaults.buttonColors(
                  containerColor = Color(0xFFEFBE42)
               )
            ) {
               Text(
                  text = "Save",
                  modifier = Modifier.padding(4.dp),
                  color = Color.White,
                  fontSize = 17.sp
               )

            }


         }
      }
   }}