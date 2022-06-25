package com.example.weatherapp.api.model

data class LocationResponse(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String?
)
