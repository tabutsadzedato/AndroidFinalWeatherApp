package com.example.weatherapp.api

import com.example.weatherapp.api.model.ForecastResponse
import com.example.weatherapp.api.model.LocationResponse
import com.example.weatherapp.api.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("data/2.5/weather")
    fun weatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric"
    ): Call<WeatherResponse>

    @GET("data/2.5/forecast")
    fun forecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") cnt: Int = 4,
        @Query("units") units: String = "metric"
    ): Call<ForecastResponse>

    @GET("geo/1.0/direct")
    fun location(@Query("q") query: String, @Query("limit") limit: Int): Call<List<LocationResponse>>
}