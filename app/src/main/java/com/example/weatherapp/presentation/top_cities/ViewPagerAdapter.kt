package com.example.weatherapp.presentation.top_cities

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherapp.database.entity.CityEntity

class ViewPagerAdapter(fragment: Fragment, private val cities: List<CityEntity>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount() = cities.size

    override fun createFragment(position: Int): Fragment {
        return CityFragment().apply {
            val bundle = Bundle()
            bundle.putParcelable(CityEntity.CITY_KEY, cities[position])
            arguments = bundle
        }
    }
}