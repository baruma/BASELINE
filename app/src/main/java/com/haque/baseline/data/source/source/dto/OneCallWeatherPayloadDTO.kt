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
    @Json(name = "generationtime_ms")
    val generationtimeMS: Double,

    @Json(name = "utc_offset_seconds")
    val utcOffsetSeconds: Long,
    val timezone: String,
    @Json(name = "timezone_abbreviation")
    val timezoneAbbreviation: String,
    val elevation: Double,

//    @Json(name = "hourly_units")
//    val hourlyUnits: HourlyUnits,
//
//    @Json(name = "daily_units")
//    val dailyUnits: DailyUnits,

    @field:Json(name = "daily")
    val dailyWeather: DailyWeatherDTO,

    @field:Json(name = "current_weather")
    val currentWeather: CurrentWeatherDTO
)