package com.haque.baseline.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.haque.baseline.R
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.databinding.WeatherFragmentBinding
import com.haque.baseline.ui.hourly.HourlyRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel
    private lateinit var hourlyWeatherRecyclerAdapter: HourlyRecyclerAdapter

//    private val hourlyWeatherObserver: Observer<List<HourlyWeatherData>> =
//        Observer<List<HourlyWeatherData>> { hourlyData ->
//            // How do you update RecyclerView code here?
////            hourlyWeatherRecyclerAdapter.
//
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_fragment)

        currentWeatherViewModel = ViewModelProvider(this)[CurrentWeatherViewModel::class.java]

        // a coroutine scope needs to be written to call suspend functions.  This lets you organize coroutines into groupings.
        // CoroutineScope's most common scopes are Default, IO and Main.  Default is for heavy computation.
        // Launch is the builder.
        CoroutineScope(IO).launch {
            getWeather()
        }

        val binding: WeatherFragmentBinding = DataBindingUtil.setContentView(
            this, R.layout.weather_fragment)

        hourlyWeatherRecyclerAdapter = HourlyRecyclerAdapter(mutableListOf())
        binding.hourlyWeatherRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.hourlyWeatherRecyclerview.adapter = hourlyWeatherRecyclerAdapter

//        currentWeatherViewModel.hourlyWeatherDataResponse.observe(binding.lifecycleOwner!!,
//            hourlyWeatherObserver)
    }

    private suspend fun getWeather() {
        currentWeatherViewModel.getHourlyWeather()
        Timber.tag("eggos").e(currentWeatherViewModel.getHourlyWeather().toString())
    }
}

