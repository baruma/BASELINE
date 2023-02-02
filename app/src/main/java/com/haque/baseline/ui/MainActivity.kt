package com.haque.baseline.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.haque.baseline.R
import com.haque.baseline.databinding.WeatherFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

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

    }

    private suspend fun getWeather() {
        currentWeatherViewModel.getWeather()
        Timber.tag("eggos").e(currentWeatherViewModel.getWeather().toString())
    }
}