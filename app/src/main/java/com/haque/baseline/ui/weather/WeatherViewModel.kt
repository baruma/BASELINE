package com.haque.baseline.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haque.baseline.data.mappers.*
import com.haque.baseline.data.model.DailyForecastedData
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.data.model.OneCallWeatherPayloadData
import com.haque.baseline.data.model.PlaceData
import com.haque.baseline.domain.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
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
        val currentWeather = result.currentWeather.toCurrentWeatherData()
        // TODO: Write Shared Preferences code before tackling the rest of this flow.
//        if(usingCelsius) {
//            currentWeather = currentWeather.copy(temperatureInFahrenheit = convertFahrenheitToCelsius(currentWeather.temperatureInFahrenheit))
//        }

        val mappedResult = OneCallWeatherPayloadData(
            currentWeather,
            result.dailyWeather.toDailyForecastedData(),
            result.hourlyWeather.toHourlyWeather()
        )

        _oneCallWeatherPayload.postValue(mappedResult)
        _hourlyWeatherData.postValue(mappedResult.hourlyWeather)
        _dailyForecastedWeatherData.postValue(mappedResult.dailyWeather)
    }

    fun convertFahrenheitToCelsius(tempInFahrenheit: Double): Double {
        val celsius = ((tempInFahrenheit  -  32) *  5)/9

        return celsius
    }
}