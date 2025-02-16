package com.creative.weather_app.network

import com.creative.weather_app.model.weatherResponse
import com.creative.weather_app.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface WeatherAPI {
    @GET("forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = API_KEY

    ): weatherResponse
}