package com.example.weatherapp.presentation.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.Utils
import com.example.weatherapp.api.RetrofitClient
import com.example.weatherapp.api.model.ForecastResponse
import com.example.weatherapp.api.model.LocationResponse
import com.example.weatherapp.api.model.WeatherResponse
import com.example.weatherapp.database.entity.CityEntity
import com.example.weatherapp.view.WeatherDataView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment(R.layout.fragment_search_layout) {

    private var currentCity = "Brooklyn"
    private val adapter = ForecastAdapter()
    private lateinit var nameTv: TextView
    private lateinit var weatherIv: ImageView
    private lateinit var weatherTv: TextView
    private lateinit var cityIv: ImageView
    private lateinit var data: WeatherDataView
    private lateinit var searchBtn: Button
    private lateinit var searchEt: EditText

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.forecastRv).apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = this@SearchFragment.adapter
        }
        nameTv = view.findViewById(R.id.cityTv)
        weatherIv = view.findViewById(R.id.weatherIv)
        weatherTv = view.findViewById(R.id.weatherTv)
        cityIv = view.findViewById(R.id.cityIv)
        data = view.findViewById(R.id.dataView)
        searchEt = view.findViewById(R.id.searchEt)
        searchBtn = view.findViewById(R.id.searchBtn)
        searchBtn.setOnClickListener {
            val city = searchEt.text.toString()
            if (city.isBlank() || city == currentCity) return@setOnClickListener
            else {
                currentCity = city
                getCoordinates(currentCity)
            }
        }
        getCoordinates(currentCity)
    }

    private fun getCoordinates(city: String) {
        RetrofitClient.weatherAPI.location(city, 1).enqueue(object :
            Callback<List<LocationResponse>> {
            override fun onResponse(
                call: Call<List<LocationResponse>>,
                response: Response<List<LocationResponse>>
            ) {
                val body = response.body()
                if (body == null || body.isEmpty())
                    showNoCityFoundToast()
                else getWeather(body?.first()!!.lat, body.first()?.lon)
            }

            override fun onFailure(call: Call<List<LocationResponse>>, t: Throwable) {
                showNoCityFoundToast()
            }
        })
    }

    fun getWeather(lat: Double, lon: Double) {
        RetrofitClient.weatherAPI.weatherData(lat, lon).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                val weather = response.body()!!
                val city = CityEntity(
                    name = weather.name,
                    temp = weather.main.temp,
                    iconId = weather.weather.first().icon,
                    imageRes = Utils.CITY_IMAGES.random(),
                    windSpeed = weather.wind.speed,
                    humidity = weather.main.humidity,
                    pressure = weather.main.pressure
                )
                getForecast(lat, lon)
                setUI(city)
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                showNoCityFoundToast()
            }
        })
    }

    fun getForecast(lat: Double, lon: Double) {
        RetrofitClient.weatherAPI.forecast(lat, lon).enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>
            ) {
                val forecast = response.body()!!
                if(forecast.list.isEmpty()) return
                adapter.setItems(forecast.list)
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                Log.d("onFailure", t.message.toString())
            }
        })
    }

    fun setUI(city: CityEntity) {
        nameTv.text = city.name
        weatherTv.text = "${city.temp}°C"
        Glide.with(requireContext()).load(Utils.getIconUrl(city.iconId)).into(weatherIv)
        Glide.with(requireContext()).load(city.imageRes).into(cityIv)
        data.setData(
            humidity = city.humidity,
            pressure = city.pressure,
            windSpeed = city.windSpeed
        )
    }

    private fun showNoCityFoundToast() {
        Toast.makeText(
            requireContext(),
            "No city found",
            Toast.LENGTH_SHORT
        ).show()
    }
}