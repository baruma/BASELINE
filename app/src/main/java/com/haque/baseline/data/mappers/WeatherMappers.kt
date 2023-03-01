package com.haque.baseline.data.mappers

import com.haque.baseline.data.model.CurrentWeatherData
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.data.model.DailyForecastedData
import com.haque.baseline.data.model.OneCallWeatherPayloadData
import com.haque.baseline.data.source.source.dto.CurrentWeatherDTO
import com.haque.baseline.data.source.source.dto.DailyWeatherDTO
import com.haque.baseline.data.source.source.dto.HourlyWeatherDTO
import com.haque.baseline.data.source.source.dto.OneCallWeatherPayloadDTO
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun HourlyWeatherDTO.toHourlyWeather(): List<HourlyWeatherData> {
    return time.mapIndexed { index, time ->
        val temperatureInFahrenheit = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
//        val pressure = pressures[index]
        val humidity = humidities[index]
        val visibility = visibilities[index]
        val precipitation = precipitations[index]
        val apparentTemperature = apparentTemperatures[index]
        HourlyWeatherData(
            time = LocalDate.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            temperatureInFahrenheit,
            apparentTemperature,
            precipitation,
            humidity,
            weatherCode,
            windSpeed,
            visibility
        )
    }
}

fun CurrentWeatherDTO.toCurrentWeatherData(): CurrentWeatherData {
    return CurrentWeatherData(
        time = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
        temperatureInFahrenheit = temperature,
        windSpeed = windSpeed,
        windDirection = windDirection,
        weatherCode = weatherCode
    )
}

fun DailyWeatherDTO.toDailyForecastedData(): List<DailyForecastedData> {
    return time.mapIndexed { index, time ->
        val weatherCode = weatherCodes[index]
        val maxTemperature = maxTemperatures[index]
        val minTemperature = minTemperatures[index]
        val precipitationSum = precipitationSums[index]
        val precipitationHours = precipitationHours[index]
        DailyForecastedData(
            time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            weatherCode,
            maxTemperature,
            minTemperature,
            precipitationSum,
            precipitationHours
        )
    }
}

fun OneCallWeatherPayloadDTO.toOneCallWeatherPayloadData(): OneCallWeatherPayloadData {
    return OneCallWeatherPayloadData(
        this.currentWeather.toCurrentWeatherData(),
        this.dailyWeather.toDailyForecastedData(),
        this.hourlyWeather.toHourlyWeather()
    )
}