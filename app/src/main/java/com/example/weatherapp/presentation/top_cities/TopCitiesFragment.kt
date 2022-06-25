package com.example.weatherapp.presentation.top_cities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.weatherapp.R
import com.example.weatherapp.Utils.TOP_CITIES
import com.example.weatherapp.WeatherApp

class TopCitiesFragment: Fragment(R.layout.fragment_top_cities_layout) {

    private lateinit var viewPager2: ViewPager2
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager2 = view.findViewById(R.id.viewPager)

        val cities = WeatherApp.roomDatabase.cityDao().getCities()
        viewPager2.adapter = ViewPagerAdapter(this, cities!!)
    }
}