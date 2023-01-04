package com.haque.baseline.data.model

import java.time.LocalDateTime

data class CurrentWeather (
    val time: LocalDateTime,
    val temperatureInFahrenheit: Double,
    val pressure: Double,
        )

"hourly_units": {
    "time": "iso8601",
    "temperature_2m": "°F",
    "relativehumidity_2m": "%",
    "apparent_temperature": "°F",
    "precipitation": "inch",
    "weathercode": "wmo code",
    "visibility": "m",
    "windspeed_10m": "mp/h"

