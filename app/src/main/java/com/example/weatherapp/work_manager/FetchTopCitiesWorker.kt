package com.example.weatherapp.work_manager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.weatherapp.Utils.CITY_IMAGES
import com.example.weatherapp.Utils.TOP_CITIES
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.api.RetrofitClient
import com.example.weatherapp.api.model.LocationResponse
import com.example.weatherapp.api.model.WeatherResponse
import com.example.weatherapp.database.entity.CityEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchTopCitiesWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private val cityDao = WeatherApp.roomDatabase.cityDao()
    override fun doWork(): Result {
        getCoordinates(TOP_CITIES)
        return Result.success()
    }

    private fun getCoordinates(cities: List<String>) {
        cities.forEach {
            RetrofitClient.weatherAPI.location(it, 1).enqueue(object :
                Callback<List<LocationResponse>> {
                override fun onResponse(
                    call: Call<List<LocationResponse>>,
                    response: Response<List<LocationResponse>>
                ) {
                    val body = response.body()
                    getWeather(body?.first()!!.lat, body.first()?.lon)
                }

                override fun onFailure(call: Call<List<LocationResponse>>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                }
            })
        }
    }

    fun getWeather(lat: Double, lon: Double) {
        RetrofitClient.weatherAPI.weatherData(lat, lon).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                val weather = response.body()!!
                cityDao.insertCity(
                    CityEntity(
                        name = weather.name,
                        temp = weather.main.temp,
                        iconId = weather.weather.first().icon,
                        imageRes = CITY_IMAGES.random(),
                        windSpeed = weather.wind.speed,
                        humidity = weather.main.humidity,
                        pressure = weather.main.pressure
                    )
                )
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("onFailure", t.message.toString())
            }
        })
    }
}