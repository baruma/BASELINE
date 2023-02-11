package com.haque.baseline.ui.currentWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haque.baseline.data.mappers.toOneCallWeatherPayloadData
import com.haque.baseline.data.model.DailyForecastedData
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.data.model.OneCallWeatherPayloadData
import com.haque.baseline.domain.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
The @Inject below tells Dagger to go look for dependencies listed after it.  Dagger will go to
AppModule (because it is annotated with @Module) and look for relevant dependencies.
 */

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
    //    private val locationFinder: LocationFinder
) : ViewModel() {

    // TODO: Remove this variable, because all the data exists in the livedata.
    lateinit var oneCallWeatherPayload: OneCallWeatherPayloadData

    private var _hourlyWeatherData = MutableLiveData<List<HourlyWeatherData>>()
    val hourlyWeatherDataResponse: LiveData<List<HourlyWeatherData>>
        get() = _hourlyWeatherData

    private var _dailyForecastedWeatherData = MutableLiveData<List<DailyForecastedData>>()
    val dailyForecastedWeatherData: LiveData<List<DailyForecastedData>>
        get() = _dailyForecastedWeatherData

    /*
    TODO: Refactor the 3 functions below.  Make the network call once, and then parse the hourly and
    daily weather data within that function.
    */

    suspend fun getOneCallWeatherData() {
        val result = repository.getOneCallAPIResponse(37.76, -122.39)
        oneCallWeatherPayload = result.toOneCallWeatherPayloadData()
    }

    fun getHourlyWeatherDataFromOneCallWeatherData(payload: OneCallWeatherPayloadData) {
        _hourlyWeatherData.postValue(payload.hourlyWeather)
    }

    fun getDailyForecastedWeatherFromOneCallWeatherData(payload: OneCallWeatherPayloadData) {
        _dailyForecastedWeatherData.postValue(payload.dailyWeather)
    }

}
