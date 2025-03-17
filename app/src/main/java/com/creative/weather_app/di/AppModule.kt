package com.creative.weather_app.di

import android.content.Context
import androidx.room.Room
import com.creative.weather_app.data.WeatherDAO
import com.creative.weather_app.data.WeatherDatabase
import com.creative.weather_app.network.WeatherAPI
import com.creative.weather_app.utils.Constants.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDAO
    = weatherDatabase.weatherDao()

    @Singleton
    @Provides
    fun provideAppDB(@ApplicationContext context: Context): WeatherDatabase
    = Room.databaseBuilder(
        context,
        WeatherDatabase::class.java,
        "weather_database")
        .fallbackToDestructiveMigration()
        .build()


    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Pass the OkHttpClient here
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)

    }

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Log request and response bodies
    }
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}