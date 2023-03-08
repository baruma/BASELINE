package com.haque.baseline.data.model

import com.haque.baseline.utils.WeatherCodeToIcon
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class DailyForecastedData(
    val time: String,
    val weatherCode: WeatherCodeToIcon,
    val maxTemperature: Double,
    val minTemperature: Double,
    val precipitationSum: Double,
    val precipitationHours: Double,
)