package com.example.weatherapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.api.model.Main
import com.example.weatherapp.api.model.Wind

class WeatherDataView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private var humidity: TextView
    private var pressure: TextView
    private var windSpeed: TextView
    init {
        inflate(context, R.layout.weather_data_view, this)
        humidity = findViewById(R.id.humidityTv)
        pressure = findViewById(R.id.pressureTv)
        windSpeed = findViewById(R.id.windTv)
    }

    fun setData(humidity: Double, pressure: Double, windSpeed: Double) {
        this.humidity.text = humidity.toString()
        this.pressure.text = pressure.toString()
        this.windSpeed.text = windSpeed.toString()
    }
}