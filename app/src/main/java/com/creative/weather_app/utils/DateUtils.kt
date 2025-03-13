package com.creative.weather_app.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtils {
    companion object {
         fun convertTimeStampToDate(timeStamp: Long): String {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = Date(timeStamp * 1000)
            return sdf.format(date)
        }

         fun convertTimeStampToHour(timeStamp: Long): String {
            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = Date(timeStamp * 1000)
            return sdf.format(date)

        }

         fun convertTimeStampToDay(timeStamp: Long): String {
            val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
            val date = Date(timeStamp * 1000)
            return sdf.format(date)
        }
        fun formatDate(dt: Int): String {

            val timeStamp = dt.toLong()
            val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
            val date = Date(timeStamp * 1000)
            return sdf.format(date)

        }
        fun convertIntToDay(day: Int): String {
            return when (day) {
                0 -> "Sunday"
                1 -> "Monday"
                2 -> "Tuesday"
                3 -> "Wednesday"
                4 -> "Thursday"
                5 -> "Friday"
                6 -> "Saturday"
                else -> ""
            }
        }
    }
}