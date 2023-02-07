package com.haque.baseline.ui.currentWeather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
import com.haque.baseline.ui.search.SearchFragment
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
    lateinit var bottomNav : BottomNavigationView


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

//        loadFragment()
//        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
//        bottomNav.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.home -> {
//                    loadFragment(HomeFragment())
//                    true
//                }
//                R.id.search_fragment -> {
//                    loadFragment(SearchFragment())
//                    true
//                }
//
//            }
//        }
    }

    private suspend fun getAllWeather() {
        currentWeatherViewModel.getOneCallWeatherData()
        currentWeatherViewModel.getHourlyWeatherDataFromOneCallWeatherData(currentWeatherViewModel.oneCallWeatherPayload)
        currentWeatherViewModel.getDailyForecastedWeatherFromOneCallWeatherData(currentWeatherViewModel.oneCallWeatherPayload)
    }

//    private  fun loadFragment(fragment: Fragment){
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.current_weather_view, fragment)
//        transaction.commit()
//    }

}

// Line 35.
// a coroutine scope needs to be written to call suspend functions.  This lets you organize coroutines into groupings.
// CoroutineScope's most common scopes are Default, IO and Main.  Default is for heavy computation.
// Launch is the builder.