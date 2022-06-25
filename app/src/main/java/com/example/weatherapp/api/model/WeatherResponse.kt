package com.example.weatherapp.api.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord")
    val coordinates: Coordinates,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    @SerializedName("sys")
    val system: WeatherSystem,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: Long
)

data class Coordinates(
    @SerializedName("lon")
    val longitude: Double,
    @SerializedName("lat")
    val latitude: Double
)

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    val pressure: Double,
    val humidity: Double
)

data class Wind(
    val speed: Double,
    val deg: Long
)

data class Clouds(
    val all: Long
)

data class WeatherSystem(
    val type: Long,
    val id: Long,
    val message: Double,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)