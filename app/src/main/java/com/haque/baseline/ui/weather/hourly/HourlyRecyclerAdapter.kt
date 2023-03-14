package com.haque.baseline.ui.weather.hourly

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haque.baseline.R
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.databinding.HourlyWeatherCardBinding
import timber.log.Timber

class HourlyRecyclerAdapter(private val dataList: MutableList<HourlyWeatherData>) :
    RecyclerView.Adapter<HourlyRecyclerAdapter.HourlyViewHolder>() {

    class HourlyViewHolder(private var binding: HourlyWeatherCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hourlyWeather: HourlyWeatherData) {

            binding.hourlyData = hourlyWeather

            binding.hourlyCardTemperatureTextview.text =
                ("${hourlyWeather.temperatureInFahrenheit}°")

            binding.hourlyCardTimeTextview.text = hourlyWeather.time

            Timber.d("SCREAMING HOURLY TIMES FROM ADAPTER - ${hourlyWeather.time}")

            binding.hourlyCardWeatherIconImageview.setImageResource(hourlyWeather.weatherCode.iconResource)
            binding.hourlyHumidityTextview.text = ("humidity: ${hourlyWeather.humidity}%")
            binding.hourlyPrecipitationTextview.text = ("precipitation: ${hourlyWeather.precipitation.toString()}")

            binding.executePendingBindings()
        }
    }

    fun updateRecyclerDate(newDataList: List<HourlyWeatherData>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val binding: HourlyWeatherCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.hourly_weather_card,
            parent,
            false
        )
        return HourlyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

    override fun onBindViewHolder(viewHolder: HourlyViewHolder, position: Int) {
        val data: HourlyWeatherData = dataList[position]
        viewHolder.bind(data)
    }
}
