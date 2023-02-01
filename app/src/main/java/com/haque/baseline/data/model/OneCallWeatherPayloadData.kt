package com.haque.baseline.data.model

data class OneCallWeatherPayloadData(
    val currentWeather: CurrentWeatherData,
    val dailyWeather: Map<Int, List<DailyForecastedData>>,
    val hourlyWeather: Map<Int, List<HourlyWeatherData>>
)