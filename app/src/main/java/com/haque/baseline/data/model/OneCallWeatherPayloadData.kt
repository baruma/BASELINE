package com.haque.baseline.data.model

data class OneCallWeatherPayloadData(
    val currentWeather: CurrentWeatherData,
    val dailyWeather: List<DailyForecastedData>,
    val hourlyWeather: List<HourlyWeatherData>
)