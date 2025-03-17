package com.creative.weather_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "setting_table")
data class Setting_Unit(
    @Nonnull
    @PrimaryKey
    @ColumnInfo("unit") val unit: String
)
