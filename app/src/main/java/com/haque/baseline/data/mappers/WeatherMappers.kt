package com.haque.baseline.data.mappers

/*
The role of this class is to map the Hourly and Weather DTO objects to Domain objects.

data class HourlyWeatherDTO (
    private val weatherDataDTO: WeatherDataDTO
)

data class WeatherDataDTO (
        val time: List<String>,

        @field:Json(name = "temperature_2m")
        val temperatures: List<Double>,

        @field:Json(name = "apparent_temperature")
        val apparentTemperatures: List<Double>,

        @field:Json(name = "relativehumidity_2m")
        val humidities: List<Double>,

        @field:Json(name = "precipitation")
        val precipitations: List<Double>,

        @field:Json(name = "weathercode")
        val weatherCodes: List<Int>,

        @field:Json(name = "pressure_msl")
        val pressures: List<Double>,

        @field:Json(name = "windspeed_10m")
        val windSpeeds: List<Double>,

        @field:Json(name = "visibility")
        val visibilities: List<Double>,

        )

        The HourlyWeatherDTO holds the WeatherDataDTO, but I need to seperate the hours out of their
        list to map to their corresponding WeatherData, because imagine the data from Open-Meteo (API
        being used as of 1.3.2023) comes in lines of doubles.
        hour 1  3.3 89%
        hour 2  2.1 20%
        hour 3  1.3 98%
        etc.    etc.

        It's sorta different from how OpenWeatherMap does it where everything is nested into each other.
        Here, you need to find each hour and put them in a list, then group up the weather data with their
        corresponding indexes and assign them to the hourly

        or just assign all the indexes together at once since you should be getting a payload of 168 data items.



 */

// This object was made so that it would be easier to find days out of a week vs. days out of a month.
// the index range is 0-6.  The index is paired with WeatherData which has time and weather data.
// WeatherINfo
//private data class IndexedWeatherData (
//    val index: Int,
//    val data: WeatherData
//)


//class WeatherMappers {
//
//}