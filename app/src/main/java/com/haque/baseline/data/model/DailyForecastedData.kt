package com.haque.baseline.data.model

import java.time.LocalDate

data class DailyForecastedData(
    val time: LocalDate,
    val weatherCode: Long,
    val maxTemperature: Double,
    val minTemperature: Double,
    val precipitationSum: Double,
    val precipitationHours: Double,
)