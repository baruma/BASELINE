package com.haque.baseline.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haque.baseline.data.mappers.toOneCallWeatherPayloadData
import com.haque.baseline.data.model.DailyForecastedData
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.data.model.OneCallWeatherPayloadData
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
    lateinit var oneCallWeatherPayload: OneCallWeatherPayloadData

    private var _hourlyWeatherData = MutableLiveData<List<HourlyWeatherData>>()
    val hourlyWeatherDataResponse: LiveData<List<HourlyWeatherData>>
        get() = _hourlyWeatherData

    private var _dailyForecastedWeatherData = MutableLiveData<List<DailyForecastedData>>()
    val dailyForecastedWeatherData: LiveData<List<DailyForecastedData>>
        get() = _dailyForecastedWeatherData

    // Don't want to have too many get Functions because there's repetitve network calls going on.
    suspend fun getOneCallWeatherData() {
        val result = repository.testAPIResponse(37.76,-122.39)
        oneCallWeatherPayload = result.toOneCallWeatherPayloadData()
    }

    fun getHourlyWeatherDataFromOneCallWeatherData(payload: OneCallWeatherPayloadData) {
        _hourlyWeatherData.postValue(payload.hourlyWeather)
    }

    fun getDailyForecastedWeatherFromOneCallWeatherData(payload: OneCallWeatherPayloadData) {
        _dailyForecastedWeatherData.postValue(payload.dailyWeather)
    }
}