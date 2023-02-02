package com.haque.baseline.data.mappers

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
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
import java.util.*

/////////////////////
// Hourly Weather //
////////////////////
private data class IndexedHourlyWeather (
    val index: Int,
    val data: HourlyWeatherData
)

//Hourly payload has 168 values. Indexing 168 values will take time.
fun HourlyWeatherDTO.toHourlyWeather(): Map<Int, List<HourlyWeatherData>> {
    // mapIndexed returns a list containing the results of applying the given transform function to
    // each element and its index in the original array.

    // 168 time values. It's just a massless list... and it doesn't come with it's own index.
    // So time is going to be assigned an indexed with '.mapIndexed.'

    return time.mapIndexed { index, time ->
        val temperatureInFahrenheit = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
//        val pressure = pressures[index]
        val humidity = humidities[index]
        val visibility = visibilities[index]
        val precipitation = precipitations[index]
        val apparentTemperature = apparentTemperatures[index]

        // IndexedHourlyWeather takes the index by '.mapIndexed' to bundle up the Hourly data.
        IndexedHourlyWeather(
            index = index,
            data = HourlyWeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                temperatureInFahrenheit = temperatureInFahrenheit,
//                pressure = pressure,
                humidity = humidity,
                // Should create a mapper for the codes and icons.
                weatherCode = weatherCode,
                apparentTemperatureInFahrenheit = apparentTemperature,
                visibility = visibility,
                windSpeed = windSpeed
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }  // despite being all the way down here, we're still within
        // the scope of IndexedHourlyWeather.  We're appending to it. }
    }
}

//////////////////////
// Current Weather //
//////////////////////

fun CurrentWeatherDTO.toCurrentWeatherData(): CurrentWeatherData {
    return CurrentWeatherData(
        time = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
        temperatureInFahrenheit = temperature,
        windSpeed = windSpeed,
        windDirection = windDirection,
        weatherCode = weatherCode
    )
}

///////////////////
// Daily Weather //
///////////////////

private data class IndexedDailyWeather(
    val index: Int,  // day of week
    val data: DailyForecastedData
)

@RequiresApi(Build.VERSION_CODES.O)
fun DailyWeatherDTO.toDailyForecastedData(): Map<Int, List<DailyForecastedData>> {
    return time.mapIndexed { index, time ->
        val weatherCode = weatherCodes[index]
        val maxTemperature = maxTemperatures[index]
        val minTemperature = minTemperatures[index]
        val precipitationSum = precipitationSums[index]
        val precipitationHours = precipitationHours[index]

        // Left off here.  Strange Run.  Right I was having issues mapping Daily Weather.
        IndexedDailyWeather(
            index = index,
            data = DailyForecastedData(
                time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                weatherCode = weatherCode,
                maxTemperature = maxTemperature,
                minTemperature = minTemperature,
                precipitationSum = precipitationSum,
                precipitationHours = precipitationHours
            ))
}.groupBy {
    it.index / 7
    }.mapValues {
        it.value.map { it.data }
    }
}

///////////////////////////////
// One Call Weather Payload //
///////////////////////////////

fun OneCallWeatherPayloadDTO.toOneCallWeatherPayloadData(): OneCallWeatherPayloadData {
    return OneCallWeatherPayloadData (
        this.currentWeather.toCurrentWeatherData(),
        this.dailyWeather.toDailyForecastedData(),
        this.hourlyWeather.toHourlyWeather()
    )
}