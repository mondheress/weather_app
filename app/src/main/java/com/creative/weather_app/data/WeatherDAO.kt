package com.creative.weather_app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.creative.weather_app.model.Favorite
import com.creative.weather_app.model.Setting_Unit
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDAO {

    @Query("SELECT * from fav_tab")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * from fav_tab where city =:city")
    suspend fun getFavById(city: String): Favorite

    @Query("DELETE from fav_tab")
    suspend fun deleteAllFavorites()

    @Query("DELETE from fav_tab where city =:city")
    suspend fun deleteFavorite(city: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    // Unit Table

    @Query("SELECT * from setting_table")
    fun getUnit(): Flow<List<Setting_Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(settingUnit: Setting_Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(settingUnit: Setting_Unit)

    @Query("DELETE from setting_table")
    suspend fun deleteAllUnits()
}