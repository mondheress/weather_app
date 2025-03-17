package com.creative.weather_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull


@Entity(tableName = "fav_tab")
data class Favorite(
    @Nonnull
    @PrimaryKey
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "country")
    val country: String,
)

