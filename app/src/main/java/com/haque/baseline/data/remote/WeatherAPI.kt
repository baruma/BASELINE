package com.haque.baseline.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    /*
    This API Call Includes
    ------------------------
    - Data from Open-Meteo's WeatherForecastAPI.  It is set to:
        - Detect GPS Location
        - Temperature (2m)
        - Relative Humidity (2m)
        - Apparent Temperature
        - Precipitation
        - Weather Code
        - Visibility
        - Wind Speed (10 m)
    - I did not explicitely tag anything from Daily Weather.
    - Settings: F°, mph, Inch, ISO, Automatically Detect Time Zone
    - The payload is very tame and needs the hourly times mapped to its corresponding data (temp, humidity, etc.)
     */
    @GET("https://api.open-meteo.com/v1/forecast?latitude=37.76&longitude=-122.39&hourly=temperature_2m,relativehumidity_2m,apparent_temperature,precipitation,weathercode,visibility,windspeed_10m&current_weather=true&temperature_unit=fahrenheit&windspeed_unit=mph&precipitation_unit=inch&timezone=auto")
    // suspend declares that this is a coroutine performing an async function.
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ) : HourlyWeatherDTO

}