package com.haque.baseline.data.remote

import com.squareup.moshi.Json

// From Hourly Payload to Trim little lists of weather details.

data class WeatherDataDTO (
        val time: List<String>,

        @field:Json(name = "temperature_2m")
        val temperatures: List<Double>,

        @field:Json(name = "apparent_temperature")
        val apparentTemperatures: List<Double>,

        @field:Json(name = "relativehumidity_2m")
        val humidities: List<Double>,

        @field:Json(name = "precipitation")
        val precipitations: List<Double>,

        @field:Json(name = "weathercode")
        val weatherCodes: List<Int>,

        @field:Json(name = "pressure_msl")
        val pressures: List<Double>,

        @field:Json(name = "windspeed_10m")
        val windSpeeds: List<Double>,

        @field:Json(name = "visibility")
        val visibilities: List<Double>,

        )

/*
Oh myyyy what is this?  Ths is a teaser into the future.  For scoping purposes, the project
will not be using this portion of the API, although I considered it.

&current_weather=true&temperature_unit=fahrenheit&windspeed_unit=mph&precipitation_unit=inch&timezone=auto")
 */
