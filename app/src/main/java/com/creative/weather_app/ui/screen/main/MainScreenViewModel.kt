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

    suspend fun getWeatherDatav2(city: String): DataofException<weatherResponse, Boolean, Exception> {
        return repository.getWeather(cityQuery = city)
    }


}