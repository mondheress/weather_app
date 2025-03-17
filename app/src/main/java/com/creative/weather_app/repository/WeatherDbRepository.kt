package com.creative.weather_app.repository

import com.creative.weather_app.data.WeatherDAO
import com.creative.weather_app.model.Favorite
import com.creative.weather_app.model.Setting_Unit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDAO){

    fun getFavorites():Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)
    suspend fun getFavById(city:String): Favorite = weatherDao.getFavById(city)
    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite.city)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()

    fun getUnit():Flow<List<Setting_Unit>> = weatherDao.getUnit()
    suspend fun insertUnit (settingUnit: Setting_Unit) = weatherDao.insertUnit(settingUnit)
    suspend fun updateUnit (settingUnit: Setting_Unit) = weatherDao.updateUnit(settingUnit)
    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()


}