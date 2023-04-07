package com.haque.baseline.data.mappers

import com.haque.baseline.data.model.CurrentWeatherData
import com.haque.baseline.data.model.DailyForecastedData
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.data.model.OneCallWeatherPayloadData
import com.haque.baseline.data.source.source.dto.CurrentWeatherDTO
import com.haque.baseline.data.source.source.dto.DailyWeatherDTO
import com.haque.baseline.data.source.source.dto.HourlyWeatherDTO
import com.haque.baseline.data.source.source.dto.OneCallWeatherPayloadDTO
import com.haque.baseline.utils.WeatherCodeToIcon
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val dayOfWeekFormat = DateTimeFormatter.ofPattern("EEE")
fun HourlyWeatherDTO.toHourlyWeather(): List<HourlyWeatherData> {
    return time.mapIndexed { index, time ->
        val pattern = DateTimeFormatter.ISO_LOCAL_DATE_TIME
            val localTime = LocalDateTime.parse(time, pattern)
            val formatter = DateTimeFormatter.ofPattern("dd-HH")
            val output: String = localTime.format(formatter)

            val temperatureInFahrenheit = temperatures[index]
            val weatherCode = weatherCodes[index]
            val windSpeed = windSpeeds[index]
            val humidity = humidities[index]
            val visibility = visibilities[index]
            val precipitation = precipitations[index]
            val apparentTemperature = apparentTemperatures[index]

            HourlyWeatherData(
                output,
                temperatureInFahrenheit,
                apparentTemperature,
                precipitation,
                humidity,
                weatherCode = WeatherCodeToIcon.codeToIconMapper(weatherCode),
                windSpeed,
                visibility
            )
    }
}

fun CurrentWeatherDTO.toCurrentWeatherData(): CurrentWeatherData {
    return CurrentWeatherData(
        time = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(),
        temperatureInFahrenheit = temperature,
        windSpeed = windSpeed,
        windDirection = windDirection,
        weatherCode = WeatherCodeToIcon.codeToIconMapper(weatherCode)
    )
}
fun DailyWeatherDTO.toDailyForecastedData(): List<DailyForecastedData> {

    return time.mapIndexed { index, time ->
        val localDate = LocalDate.parse(time, DateTimeFormatter.ISO_LOCAL_DATE)

        val dayOfWeek = localDate.format(dayOfWeekFormat)
        val weatherCode = weatherCodes[index]
        val maxTemperature = maxTemperatures[index]
        val minTemperature = minTemperatures[index]
        val precipitationSum = precipitationSums[index]
        val precipitationHours = precipitationHours[index]
        DailyForecastedData(
            dayOfWeek,
            weatherCode = WeatherCodeToIcon.codeToIconMapper(weatherCode),
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