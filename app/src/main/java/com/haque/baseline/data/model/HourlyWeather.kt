package com.haque.baseline.data.model

import com.squareup.moshi.Json
import java.time.LocalDateTime

/* Basically, what do you want to display in your cards.

Hourly Cards just need:
-----------------------
- Temperature
- Weather Code
- Time

IDEA: but it has all this info.... sounds like there's room in the future for a tap for details feature.
 */

data class HourlyWeather (
    val time: LocalDateTime,
    val temperature: Double,
    val apparentTemperature: Double,
    val humidity: Double,
    val weatherCode: Int,
    val pressure: Double,
    val windSpeed: Double,
    val visibility: Double
    )
