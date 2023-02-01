package com.haque.baseline.data.source.source.dto

import com.squareup.moshi.Json

data class HourlyWeatherDTO (
        val time: List<String>,

        @field:Json(name = "temperature_2m")
        val temperatures: List<Double>,

        @field:Json(name = "apparent_temperature")
        val apparentTemperatures: List<Double>,

        @field:Json(name = "relativehumidity_2m")
        val humidities: List<Long>,

        @field:Json(name = "precipitation")
        val precipitations: List<Double>,

        @field:Json(name = "weathercode")
        val weatherCodes: List<Long>,

        @field:Json(name = "pressure_msl")
        val pressures: List<Double>,

        @field:Json(name = "windspeed_10m")
        val windSpeeds: List<Double>,

        @field:Json(name = "visibility")
        val visibilities: List<Double>

        )