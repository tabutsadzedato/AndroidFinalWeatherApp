package com.example.weatherapp.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey
    val name: String,
    val temp: Double,
    val iconId: String,
    val imageRes: Int,
    val windSpeed: Double,
    val humidity: Double,
    val pressure: Double
): Parcelable {

    companion object {
        const val CITY_KEY = "city_key"
    }
}