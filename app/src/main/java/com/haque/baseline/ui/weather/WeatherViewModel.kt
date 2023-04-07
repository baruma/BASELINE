package com.haque.baseline.ui.weather

import android.content.SharedPreferences
import android.icu.util.TimeZone
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haque.baseline.data.UnitConstants
import com.haque.baseline.data.mappers.toCurrentWeatherData
import com.haque.baseline.data.mappers.toDailyForecastedData
import com.haque.baseline.data.mappers.toHourlyWeather
import com.haque.baseline.data.model.DailyForecastedData
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.data.model.OneCallWeatherPayloadData
import com.haque.baseline.data.model.PlaceData
import com.haque.baseline.domain.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import javax.inject.Inject
import kotlin.math.truncate


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

    // Default Location of NY, NY.
    val defaultCurrentLocation =
        MutableLiveData<PlaceData>(PlaceData("New York", 43.00f, -75.00f, "USA", "New York"))

    suspend fun getOneCallWeatherData(lat: Float, lon: Float) {

        val result = repository.getOneCallAPIResponse(lat, lon)
        var currentWeather = result.currentWeather.toCurrentWeatherData()
        var hourlyWeather = result.hourlyWeather.toHourlyWeather()
        var dailyWeather = result.dailyWeather.toDailyForecastedData()

        // If user sets toggle to Metric, convert, otherwise return Imperial data.
        if (sharedPreferences.getString(
                UnitConstants.unitScaleKey, UnitConstants.imperialScale
            ) == UnitConstants.metricScale
        ) {
            currentWeather = currentWeather.copy(
                temperatureInFahrenheit = convertFahrenheitToCelsius(currentWeather.temperatureInFahrenheit),
                windSpeed = convertFeetToMeters(currentWeather.windSpeed)
            )

            hourlyWeather = hourlyWeather.map {
                it.copy(
                    temperatureInFahrenheit = convertFahrenheitToCelsius(it.temperatureInFahrenheit),
                    apparentTemperatureInFahrenheit = convertFahrenheitToCelsius(it.apparentTemperatureInFahrenheit),
                    precipitation = convertInchesToMillimeters(it.precipitation),
                    windSpeed = convertMilesToKilometers(it.windSpeed)
                )
            }

            dailyWeather = dailyWeather.map {
                it.copy(
                    maxTemperature = convertFahrenheitToCelsius(it.maxTemperature),
                    minTemperature = convertFahrenheitToCelsius(it.minTemperature),
                    precipitationSum = convertInchesToMillimeters(it.precipitationSum)
                )
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

        matchCurrentTimeWithHourlyWeather()
    }

    // Get Hour from UTC and compare with Hour from Data Model
    private fun matchCurrentTimeWithHourlyWeather() {
        val userTimeZone = TimeZone.getDefault()

        Timber.tag("zone").d(userTimeZone.toString())

        /*
        What I could do is take the utc, then take the offset from systemTimeZone and subtract it from the UTC.
        Then take that result and parse the list of times for the right data.  It's excessive but it should work.
        */
    }

    private fun matchCurrentDayWithDayForecast() {}

    // Unit Conversion Functions
    // Temperature Units
    private fun convertFahrenheitToCelsius(tempInFahrenheit: Double): Double {
        val celsius = (((tempInFahrenheit - 32) * 5) / 9)
        return truncate(celsius)
    }

    private fun convertCelsiusToFahrenheit(tempInCelsius: Double): Double {
        val fahrenheit = ((tempInCelsius * 1.8) + 32)
        return truncate(fahrenheit)
    }

    // Windspeed Units
    private fun convertKilometersToMiles(windSpeedInKilometers: Double): Double {
        val miles = windSpeedInKilometers / 1.60934
        return truncate(miles)
    }

    private fun convertMilesToKilometers(windSpeedInMiles: Double): Double {
        val kilometers = windSpeedInMiles * 1.60934
        return truncate(kilometers)
    }

    // Precipitation Units
    private fun convertInchesToMillimeters(precipitationInInches: Double): Double {
        val millimeters = precipitationInInches * 25.4
        return truncate(millimeters)
    }

    private fun convertMillimetersToInches(precipitationInMillimeters: Double): Double {
        val inches = precipitationInMillimeters / 25.4
        return truncate(inches)
    }

    // Visibility Units
    private fun convertFeetToMeters(visibilityInFeet: Double): Double {
        val meters = visibilityInFeet / 3.281
        return truncate(meters)
    }

    private fun convertMetersToFeet(visibilityInMeters: Double): Double {
        val feet = visibilityInMeters * 3.281
        return truncate(feet)
    }
}