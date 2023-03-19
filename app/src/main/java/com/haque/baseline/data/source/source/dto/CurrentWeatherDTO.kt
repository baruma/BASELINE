package com.haque.baseline.data.source.source.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherDTO(

    val temperature: Double,

    @field:Json(name = "windspeed")
    val windSpeed: Double,

    @field:Json(name = "winddirection")
    val windDirection: Double,

    @field:Json(name = "weathercode")
    val weatherCode: Int,

    val time: String
)