package com.haque.baseline.data.model

import com.haque.baseline.utils.WeatherCodeToIcon
import java.time.LocalDate

data class HourlyWeatherData(
    val time: LocalDate,
    val temperatureInFahrenheit: Double,
    val apparentTemperatureInFahrenheit: Double,
    val precipitation: Double,
    val humidity: Long,
    val weatherCode: WeatherCodeToIcon,
    val windSpeed: Double,
    val visibility: Double
)
