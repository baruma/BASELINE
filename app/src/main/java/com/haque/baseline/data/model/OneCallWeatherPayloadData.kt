package com.haque.baseline.data.model

data class OneCallWeatherPayloadData(
    val currentWeather: CurrentWeatherData,
    val dailyWeather: DailyForecastedData,
    val hourlyWeather: HourlyWeatherData
)