package com.haque.baseline.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.haque.baseline.R
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // I actually don't know if I need ths line or not.  I figured it's the XML equivalent to compose
        // when setting up a HiltViewModel
        currentWeatherViewModel = ViewModelProvider(this)[CurrentWeatherViewModel::class.java]

        // a coroutine scope needs to be written to call suspend functions.  This lets you organize coroutines into groupings.
        // CoroutineScope's most common scopes are Default, IO and Main.  Default is for heavy computation.
        // Launch is the builder.
        CoroutineScope(IO).launch {
            getWeather()
        }

    }

    private suspend fun getWeather() {
        currentWeatherViewModel.getWeather()
        Timber.tag("eggos").e(currentWeatherViewModel.getWeather().toString())
    }
}