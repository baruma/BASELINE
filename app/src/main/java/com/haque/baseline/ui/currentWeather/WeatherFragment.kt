package com.haque.baseline.ui.currentWeather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.haque.baseline.R
import com.haque.baseline.data.model.DailyForecastedData
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.databinding.WeatherFragmentBinding
import com.haque.baseline.ui.currentWeather.daily.DailyWeatherRecyclerAdapter
import com.haque.baseline.ui.currentWeather.hourly.HourlyRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class WeatherFragment: Fragment() {
    // so you don't have to write factories for viewmodels anymore
    private val currentWeatherViewModel by viewModels<CurrentWeatherViewModel>()
    private lateinit var hourlyWeatherRecyclerAdapter: HourlyRecyclerAdapter
    private lateinit var dailyWeatherRecyclerAdapter: DailyWeatherRecyclerAdapter
    lateinit var bottomNav: BottomNavigationView

    private lateinit var binding: WeatherFragmentBinding

    private val hourlyWeatherObserver: Observer<List<HourlyWeatherData>> =
        Observer<List<HourlyWeatherData>> { hourlyData ->
            hourlyWeatherRecyclerAdapter.updateRecyclerDate(hourlyData)
        }

    private val dailyWeatherObserver: Observer<List<DailyForecastedData>> =
        Observer<List<DailyForecastedData>> { dailyForecastedWeather ->
            dailyWeatherRecyclerAdapter.updateRecyclerData(dailyForecastedWeather)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.weather_fragment, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        hourlyWeatherRecyclerAdapter = HourlyRecyclerAdapter(mutableListOf())
        binding.hourlyWeatherRecyclerview.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.hourlyWeatherRecyclerview.adapter = hourlyWeatherRecyclerAdapter

        dailyWeatherRecyclerAdapter = DailyWeatherRecyclerAdapter(mutableListOf())
        binding.dailyWeatherForecastRecyclerview.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.dailyWeatherForecastRecyclerview.adapter = dailyWeatherRecyclerAdapter

        currentWeatherViewModel.hourlyWeatherDataResponse.observe(
            viewLifecycleOwner,
            hourlyWeatherObserver
        )
        currentWeatherViewModel.dailyForecastedWeatherData.observe(
            viewLifecycleOwner,
            dailyWeatherObserver
        )

        CoroutineScope(Dispatchers.IO).launch {
            getAllWeather()
        }
    }

    private suspend fun getAllWeather() {
        currentWeatherViewModel.getOneCallWeatherData()
        currentWeatherViewModel.getHourlyWeatherDataFromOneCallWeatherData(currentWeatherViewModel.oneCallWeatherPayload)
        currentWeatherViewModel.getDailyForecastedWeatherFromOneCallWeatherData(
            currentWeatherViewModel.oneCallWeatherPayload
        )
    }
}