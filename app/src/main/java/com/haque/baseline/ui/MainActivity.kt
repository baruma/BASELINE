package com.haque.baseline.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haque.baseline.R
import com.haque.baseline.data.model.DailyForecastedData
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.databinding.WeatherFragmentBinding
import com.haque.baseline.ui.daily.DailyWeatherRecyclerAdapter
import com.haque.baseline.ui.hourly.HourlyRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Don't need to inject this because the OS will create an instance of MainActivity, not you.
    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel
    private lateinit var hourlyWeatherRecyclerAdapter: HourlyRecyclerAdapter
    private lateinit var dailyWeatherRecyclerAdapter: DailyWeatherRecyclerAdapter

    private val hourlyWeatherObserver: Observer<List<HourlyWeatherData>> =
        Observer<List<HourlyWeatherData>> { hourlyData ->
            hourlyWeatherRecyclerAdapter.updateRecyclerDate(hourlyData)
        }

    private val dailyWeatherObserver: Observer<List<DailyForecastedData>> =
        Observer<List<DailyForecastedData>> { dailyForecastedWeather ->
            dailyWeatherRecyclerAdapter.updateRecyclerData(dailyForecastedWeather)
        }

            // should be ordered as ui, observables then networking code.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: WeatherFragmentBinding = DataBindingUtil.setContentView(
            this, R.layout.weather_fragment)

        currentWeatherViewModel = ViewModelProvider(this)[CurrentWeatherViewModel::class.java]

        hourlyWeatherRecyclerAdapter = HourlyRecyclerAdapter(mutableListOf())
        binding.hourlyWeatherRecyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.hourlyWeatherRecyclerview.adapter = hourlyWeatherRecyclerAdapter

        dailyWeatherRecyclerAdapter = DailyWeatherRecyclerAdapter(mutableListOf())
        binding.dailyWeatherForecastRecyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.dailyWeatherForecastRecyclerview.adapter = dailyWeatherRecyclerAdapter

            currentWeatherViewModel.hourlyWeatherDataResponse.observe(this,
                hourlyWeatherObserver)
            currentWeatherViewModel.dailyForecastedWeatherData.observe(this,
                dailyWeatherObserver)

        CoroutineScope(IO).launch {
            getAllWeather()
        }
    }

    private suspend fun getAllWeather() {
        currentWeatherViewModel.getOneCallWeatherData()
        currentWeatherViewModel.getHourlyWeatherDataFromOneCallWeatherData(currentWeatherViewModel.oneCallWeatherPayload)  // this shouldn't go here.
        currentWeatherViewModel.getDailyForecastedWeatherFromOneCallWeatherData(currentWeatherViewModel.oneCallWeatherPayload)
    }

}

// Line 35.
// a coroutine scope needs to be written to call suspend functions.  This lets you organize coroutines into groupings.
// CoroutineScope's most common scopes are Default, IO and Main.  Default is for heavy computation.
// Launch is the builder.