package com.creative.weather_app.ui.screen.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creative.weather_app.data.DataofException
import com.creative.weather_app.model.weatherResponse
import com.creative.weather_app.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: WeatherRepository):ViewModel() {

    val data : MutableState<DataofException<weatherResponse, Boolean, Exception>>
    = mutableStateOf(DataofException(null,true,Exception("")))
    init {
        loadWeather()
    }

    private fun loadWeather() {
        getWeatherData("tunis")
    }

    private fun getWeatherData(city: String) {

        viewModelScope.launch {
            if(city.isEmpty()) return@launch
            data.value.loading = true
            data.value = repository.getWeather(cityQuery = city)
            if(data.value.data.toString().isNotEmpty()) data.value.loading = false
            Log.d("TAG", "loadWeather: ${data.value.data}")

        }
    }

}