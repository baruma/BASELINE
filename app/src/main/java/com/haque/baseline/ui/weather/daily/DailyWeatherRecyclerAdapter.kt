package com.haque.baseline.ui.weather.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haque.baseline.R
import com.haque.baseline.data.model.DailyForecastedData
import com.haque.baseline.databinding.DayWeatherCardBinding

class DailyWeatherRecyclerAdapter(private val dataList: MutableList<DailyForecastedData>) :
    RecyclerView.Adapter<DailyWeatherRecyclerAdapter.DailyForecastedWeatherViewHolder>() {

    class DailyForecastedWeatherViewHolder(private var binding: DayWeatherCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dailyForecastedWeather: DailyForecastedData) {
            binding.executePendingBindings()
            binding.dayWeatherIconImageview.setImageResource(dailyForecastedWeather.weatherCode.iconResource)
            binding.dayCardMinTempTextview.text = ("${dailyForecastedWeather.minTemperature.toString()}°")
            binding.dayCardMaxTempTextview.text = ("${dailyForecastedWeather.maxTemperature.toString()}°")
        }
    }

    fun updateRecyclerData(newDataList: List<DailyForecastedData>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DailyForecastedWeatherViewHolder {
        val binding: DayWeatherCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.day_weather_card,
            parent,
            false
        )
        return DailyForecastedWeatherViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

    override fun onBindViewHolder(viewHolder: DailyForecastedWeatherViewHolder, position: Int) {
        val data: DailyForecastedData = dataList[position]
        viewHolder.bind(data)
    }
}