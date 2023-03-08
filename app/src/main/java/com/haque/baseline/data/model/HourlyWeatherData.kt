package com.haque.baseline.data.model

import com.haque.baseline.utils.WeatherCodeToIcon
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class HourlyWeatherData(
    val time: String,
    val temperatureInFahrenheit: Double,
    val apparentTemperatureInFahrenheit: Double,
    val precipitation: Double,
    val humidity: Long,
    val weatherCode: WeatherCodeToIcon,
    val windSpeed: Double,
    val visibility: Double
)
