package com.example.weatherapp

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getIconUrl(icon: String) = "https://openweathermap.org/img/wn/$icon@2x.png"

    val TOP_CITIES =
        listOf("Tbilisi", "Berlin", "Paris", "New-York", "Rome", "Amsterdam", "Los Angeles")

    val CITY_IMAGES = listOf(
        R.drawable.city_1,
        R.drawable.city_2,
        R.drawable.city_3,
        R.drawable.city_4,
        R.drawable.city_5,
        R.drawable.city_6,
        R.drawable.city_7,
    )

    fun getDateTime(timestamp: Long): String? {
        return try {
            val sdf = SimpleDateFormat("HH:mm dd/MM/yyyy")
            val netDate = Date(timestamp * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }
}