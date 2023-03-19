package com.haque.baseline.data.source.source.dto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OneCallWeatherPayloadDTO (

    @field:Json(name = "latitude")
    val latitude: Float,

    @field:Json(name = "longitude")
    val longitude: Float,

    @field:Json(name = "hourly")
    val hourlyWeather: HourlyWeatherDTO,

    @Json(name = "generationtime_ms")
    val generationtimeMS: Double,

    @Json(name = "utc_offset_seconds")
    val utcOffsetSeconds: Long,
    val timezone: String,
    @Json(name = "timezone_abbreviation")
    val timezoneAbbreviation: String,
    val elevation: Double,

    @field:Json(name = "daily")
    val dailyWeather: DailyWeatherDTO,

    @field:Json(name = "current_weather")
    val currentWeather: CurrentWeatherDTO
)