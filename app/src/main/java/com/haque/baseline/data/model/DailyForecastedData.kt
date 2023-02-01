package com.haque.baseline.data.model

import java.time.LocalDateTime

// Basically the Weekly Forecast Cards.

// Right now this is structured to be a whole list of weather data.  It does not parse them to
// individual days.  You could do this in the mapper.

// changed weekly to seven days because the user may not necessarily be opening the app on a mon/sun.
//data class DailyForecastedData (
//    val time: LocalDateTime,
//    val weatherCodes: List<Int>,
//    val maxTemperatures: List<Double>,
//    val minTemperatures: List<Double>,
//    val precipitationSums: List<Double>,
//    val precipitationHours: List<Double>
//    )

data class DailyForecastedData(
    val time: LocalDateTime,
    val weatherCode: Long,
    val maxTemperature: Double,
    val minTemperature: Double,
    val precipitationSum: Double,
    val precipitationHours: Double,
        )