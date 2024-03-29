package com.haque.baseline.data.source.source.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DailyWeatherDTO(

    val time: List<String>,

    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,

    @field:Json(name = "temperature_2m_max")
    val maxTemperatures: List<Double>,

    @field:Json(name = "temperature_2m_min")
    val minTemperatures: List<Double>,

    @field:Json(name = "precipitation_sum")
    val precipitationSums: List<Double>,

    @field:Json(name = "precipitation_hours")
    val precipitationHours: List<Double>
)