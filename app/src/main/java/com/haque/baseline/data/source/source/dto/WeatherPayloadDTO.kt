package com.haque.baseline.data.source.source.dto

import com.squareup.moshi.Json

data class WeatherPayloadDTO (
    @field:Json(name = "hourly")
    val hourlyWeather: HourlyWeatherDTO,

    @field:Json(name = "daily")
    val dailyWeather: DailyWeatherDTO,

    @field:Json(name = "current_weather")
    val currentWeather: CurrentWeatherDTO
    )
