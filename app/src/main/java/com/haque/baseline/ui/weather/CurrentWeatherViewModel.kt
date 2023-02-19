package com.haque.baseline.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haque.baseline.data.mappers.toOneCallWeatherPayloadData
import com.haque.baseline.data.model.DailyForecastedData
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.data.model.OneCallWeatherPayloadData
import com.haque.baseline.domain.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/*
The @Inject below tells Dagger to go look for dependencies listed after it.  Dagger will go to
AppModule (because it is annotated with @Module) and look for relevant dependencies.
 */

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
//    private val placeFinder: PlaceFinder
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

    /*
    TODO: Refactor the 3 functions below.  Make the network call once, and then parse the hourly and
    daily weather data within that function.
    */

    suspend fun getOneCallWeatherData(lat: Float, lon: Float) {
        val result = repository.getOneCallAPIResponse(lat, lon)
        val mappedResult = result.toOneCallWeatherPayloadData()

        _oneCallWeatherPayload.postValue(mappedResult)
        _hourlyWeatherData.postValue(mappedResult.hourlyWeather)
        _dailyForecastedWeatherData.postValue(mappedResult.dailyWeather)
    }

    suspend fun getUserLocation() {
//        placeFinder.getLastKnownLocation()
//        Timber.d("Current Location: ${placeFinder.getLastKnownLocation().toString()}")
    }

}