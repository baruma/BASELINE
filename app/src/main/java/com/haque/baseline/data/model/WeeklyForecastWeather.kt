package com.haque.baseline.data.model

import com.squareup.moshi.Json
import java.time.LocalDateTime

// Basically the Weekly Forecast Cards.

// Right now this is structured to be a whole list of weather data.  It does not parse them to
// individual days.  You could do this in the mapper.

data class WeeklyForecastWeather (
    val time: LocalDateTime,
    val weatherCodes: List<Int>,
    val maxTemperatures: List<Double>,
    val minTemperatures: List<Double>,
    val precipitationSums: List<Double>,
    val precipitationHours: List<Double>
    )
