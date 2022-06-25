package com.example.weatherapp.api.model

data class ForecastResponse(
    val list: List<Forecast>
)

data class Forecast(
    val dt: Long,
    val main: Main,
    val pressure: Double,
    val humidity: Double,
    val weather: List<Weather>
)