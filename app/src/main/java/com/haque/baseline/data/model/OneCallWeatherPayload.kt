package com.haque.baseline.data.model

data class OneCallWeatherPayload(
    val currentWeather: CurrentWeatherData,
    val dailyWeather: DailyForecastedData,
    val hourlyWeather: HourlyWeatherData
)