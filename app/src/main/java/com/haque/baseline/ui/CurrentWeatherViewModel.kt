package com.haque.baseline.ui

import androidx.lifecycle.ViewModel
import com.haque.baseline.data.source.source.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
The @Inject below tells Dagger to go look for dependencies listed after it.  Dagger will go to
AppModule (because it is annotated with @Module) and look for relevant dependencies.
 */
@HiltViewModel
class CurrentWeatherViewModel @Inject constructor (
    private val repository: WeatherRepository): ViewModel() {

    suspend fun getWeather() {
        repository.testAPIResponse(37.76,-122.39)
    }
}