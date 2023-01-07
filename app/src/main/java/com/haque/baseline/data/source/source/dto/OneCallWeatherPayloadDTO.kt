package com.haque.baseline.data.source.source.dto

import com.squareup.moshi.Json

data class OneCallWeatherPayloadDTO (
    @field:Json(name = "latitude")
    val latitude: Float,

    @field:Json(name = "longitude")
    val longitude: Float,

    @field:Json(name = "hourly")
    val hourlyWeather: HourlyWeatherDTO,

    // I honestly have no idea what this variable is.
    val generationtime_ms: Float,

    val utc_offset_seconds: Int,

    val timezone: String,

    val timezone_abbreviation: String,

    val elevation: Double,

    @field:Json(name = "daily")
    val dailyWeather: DailyWeatherDTO,

    @field:Json(name = "current_weather")
    val currentWeather: CurrentWeatherDTO
)
