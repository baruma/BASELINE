package com.haque.baseline.ui.weather

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haque.baseline.BuildConfig
import com.haque.baseline.data.TemperatureConstants
import com.haque.baseline.data.mappers.toCurrentWeatherData
import com.haque.baseline.data.mappers.toDailyForecastedData
import com.haque.baseline.data.mappers.toHourlyWeather
import com.haque.baseline.data.model.DailyForecastedData
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.data.model.OneCallWeatherPayloadData
import com.haque.baseline.data.model.PlaceData
import com.haque.baseline.domain.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository, private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private var _oneCallWeatherPayload = MutableLiveData<OneCallWeatherPayloadData>()
    val oneCallWeatherPayload: LiveData<OneCallWeatherPayloadData>
        get() = _oneCallWeatherPayload

    private var _hourlyWeatherData = MutableLiveData<List<HourlyWeatherData>>()
    val hourlyWeatherData: LiveData<List<HourlyWeatherData>>
        get() = _hourlyWeatherData

    private var _dailyForecastedWeatherData = MutableLiveData<List<DailyForecastedData>>()
    val dailyForecastedWeatherData: LiveData<List<DailyForecastedData>>
        get() = _dailyForecastedWeatherData


    // Using only MutableLiveData  because it has a public getter/setter that can be used in MainActivity
    // Whereas LiveData does not have this ability.

    // Default Location of NY NY
    val currentLocation =
        MutableLiveData<PlaceData>(PlaceData("New York", 43.00f, -75.00f, "USA", "New York"))

    /*
    TODO: Refactor the 3 functions below.  Make the network call once, and then parse the hourly and daily weather data within that function.
    */
    suspend fun getOneCallWeatherData(lat: Float, lon: Float) {
        val result = repository.getOneCallAPIResponse(lat, lon)
        var currentWeather = result.currentWeather.toCurrentWeatherData()
        var hourlyWeather = result.hourlyWeather.toHourlyWeather()
        var dailyWeather = result.dailyWeather.toDailyForecastedData()

        if (sharedPreferences.getString(
                TemperatureConstants.temperatureScaleKey,
                TemperatureConstants.fahrenheitScale
            ) == TemperatureConstants.celsiusScale
        ) {
            currentWeather = currentWeather.copy(temperatureInFahrenheit = convertFahrenheitToCelsius(currentWeather.temperatureInFahrenheit))

            hourlyWeather = hourlyWeather.map {
                it.copy(temperatureInFahrenheit = convertFahrenheitToCelsius(it.temperatureInFahrenheit),
                    apparentTemperatureInFahrenheit = convertFahrenheitToCelsius(it.apparentTemperatureInFahrenheit))
            }

            dailyWeather = dailyWeather.map {
                it.copy(maxTemperature = convertFahrenheitToCelsius(it.maxTemperature),
                    minTemperature = convertFahrenheitToCelsius(it.minTemperature))
            }
        }

        val mappedResult = OneCallWeatherPayloadData(
            currentWeather,
            dailyWeather,
            hourlyWeather
        )


        _oneCallWeatherPayload.postValue(mappedResult)
        _hourlyWeatherData.postValue(mappedResult.hourlyWeather)
        _dailyForecastedWeatherData.postValue(mappedResult.dailyWeather)
    }

    fun convertFahrenheitToCelsius(tempInFahrenheit: Double): Double {
        val celsius = ((tempInFahrenheit - 32) * 5) / 9

        return celsius
    }
}