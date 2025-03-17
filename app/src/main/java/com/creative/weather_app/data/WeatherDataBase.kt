package com.creative.weather_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.creative.weather_app.model.Favorite
import com.creative.weather_app.model.Setting_Unit


@Database(entities = [Favorite::class, Setting_Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDAO
}