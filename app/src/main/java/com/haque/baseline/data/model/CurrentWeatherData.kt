package com.haque.baseline.data.model

import com.haque.baseline.utils.WeatherCodeToIcon
import java.time.LocalDateTime

data class CurrentWeatherData(
    val time: LocalDateTime,
    val temperatureInFahrenheit: Double,
    val windSpeed: Double,
    val windDirection: Double,
    val weatherCode: WeatherCodeToIcon
)