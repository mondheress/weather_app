package com.creative.weather_app.repository

import android.util.Log
import com.creative.weather_app.data.DataofException
import com.creative.weather_app.model.weatherResponse
import com.creative.weather_app.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherAPI) {

    suspend fun getWeather(cityQuery: String): DataofException<weatherResponse, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e: Exception) {
            Log.d("CurrentExeception is:", e.message.toString())
            return DataofException(exception = e)
        }
        return DataofException(data = response)
    }
}