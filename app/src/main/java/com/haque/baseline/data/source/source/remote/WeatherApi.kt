package com.haque.baseline.data.source.source.remote

import com.haque.baseline.data.source.source.dto.OneCallWeatherPayloadDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    // TODO: Remove hardcoded coordinates.
    @GET("forecast?latitude=43.00&longitude=-75.00&hourly=temperature_2m,relativehumidity_2m,apparent_temperature,precipitation,visibility,weathercode,windspeed_10m&daily=weathercode,temperature_2m_max,temperature_2m_min,precipitation_sum,precipitation_hours&current_weather=true&temperature_unit=fahrenheit&windspeed_unit=mph&precipitation_unit=inch&timezone=UTC")
    suspend fun getWeatherData(
        @Query("latitude") lat: Float,
        @Query("longitude") long: Float
    ): OneCallWeatherPayloadDTO
}