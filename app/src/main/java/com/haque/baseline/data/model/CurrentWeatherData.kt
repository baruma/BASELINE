package com.haque.baseline.data.model

import com.haque.baseline.utils.WeatherCodeToIcon
import java.time.LocalDateTime

data class CurrentWeatherData(
    val time: String,
    val temperatureInFahrenheit: Double,
    val windSpeed: Double,
    val windDirection: Double,
    val weatherCode: WeatherCodeToIcon
)

// NFR: Should these values be Optional?
// NFR: If so, should I declare Optional values in DTOs or Business Models?
