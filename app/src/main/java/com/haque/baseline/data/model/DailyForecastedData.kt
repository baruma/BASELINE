package com.haque.baseline.data.model

import com.haque.baseline.utils.WeatherCodeToIcon
import java.time.LocalDate

data class DailyForecastedData(
    val time: LocalDate,
    val weatherCode: WeatherCodeToIcon,
    val maxTemperature: Double,
    val minTemperature: Double,
    val precipitationSum: Double,
    val precipitationHours: Double,
)