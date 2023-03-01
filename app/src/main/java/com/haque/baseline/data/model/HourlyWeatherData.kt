package com.haque.baseline.data.model

import java.time.LocalDate

data class HourlyWeatherData(
    val time: LocalDate,
    val temperatureInFahrenheit: Double,
    val apparentTemperatureInFahrenheit: Double,
    val precipitation: Double,
    val humidity: Long,
    val weatherCode: Long,
    val windSpeed: Double,
    val visibility: Double
)
