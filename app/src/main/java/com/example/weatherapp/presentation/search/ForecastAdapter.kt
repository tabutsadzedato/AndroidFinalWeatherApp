package com.example.weatherapp.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.Utils
import com.example.weatherapp.api.model.Forecast

class ForecastAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Forecast> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.forecast_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(items: List<Forecast>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTv: TextView
        private val weatherIv: ImageView
        private val weatherTv: TextView

        init {
            dateTv = itemView.findViewById(R.id.dateTv)
            weatherIv = itemView.findViewById(R.id.weatherIv)
            weatherTv = itemView.findViewById(R.id.weatherTv)
        }
        fun onBind(item: Forecast) {
            dateTv.text = Utils.getDateTime(item.dt)
            Glide.with(itemView.context).load(Utils.getIconUrl(item.weather.first().icon)).into(weatherIv)
            weatherTv.text = "${item.main.tempMax}°C / ${item.main.tempMin}°C"
        }
    }
}