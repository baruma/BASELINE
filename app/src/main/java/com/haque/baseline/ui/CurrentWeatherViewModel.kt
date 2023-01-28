package com.haque.baseline.ui

import androidx.lifecycle.ViewModel
import com.haque.baseline.data.source.source.repository.WeatherRepository
import com.haque.baseline.data.source.source.repository.WeatherRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/*
The @Inject below tells Dagger to go look for dependencies listed after it.  Dagger will go to
AppModule (because it is annotated with @Module) and look for relevant dependencies.
 */

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor (
    private val repository: WeatherRepository): ViewModel() {

    suspend fun getWeather() {
        val result = repository.testAPIResponse(37.76,-122.39)
        Timber.tag("blah").d(result.toString())
    }
}