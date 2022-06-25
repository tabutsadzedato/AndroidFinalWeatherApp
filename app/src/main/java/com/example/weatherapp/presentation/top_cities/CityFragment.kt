package com.example.weatherapp.presentation.top_cities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.Utils
import com.example.weatherapp.database.entity.CityEntity
import com.example.weatherapp.view.WeatherDataView

class CityFragment : Fragment(R.layout.fragment_city_layout) {

    private lateinit var nameTv: TextView
    private lateinit var weatherIv: ImageView
    private lateinit var weatherTv: TextView
    private lateinit var cityIv: ImageView
    private lateinit var data: WeatherDataView

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameTv = view.findViewById(R.id.cityTv)
        weatherIv = view.findViewById(R.id.weatherIv)
        weatherTv = view.findViewById(R.id.weatherTv)
        cityIv = view.findViewById(R.id.cityIv)
        data = view.findViewById(R.id.dataView)
        val city = arguments?.getParcelable<CityEntity>(CityEntity.CITY_KEY)
        if (city != null) {
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
    }
}