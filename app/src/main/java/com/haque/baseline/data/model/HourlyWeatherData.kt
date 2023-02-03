package com.haque.baseline.data.model

import java.time.LocalDate

/* Basically, what do you want to display in your cards.

Hourly Cards just need:
-----------------------
- Temperature
- Weather Code
- Time

IDEA: but it has all this info.... sounds like there's room in the future for a tap for details feature.
 */

data class HourlyWeatherData(
    val time: LocalDate,
    val temperatureInFahrenheit: Double,
    val apparentTemperatureInFahrenheit: Double,
    val humidity: Long,
    val weatherCode: Long,
    val windSpeed: Double,
    val visibility: Double
)
