package com.haque.baseline.data.source.source.remote

import com.haque.baseline.data.source.source.dto.PlaceDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceApi {
    @GET("forecast?latitude=37.76&longitude=-122.39&hourly=temperature_2m,relativehumidity_2m,apparent_temperature,precipitation,visibility,weathercode,windspeed_10m&daily=weathercode,temperature_2m_max,temperature_2m_min,precipitation_sum,precipitation_hours&current_weather=true&temperature_unit=fahrenheit&windspeed_unit=mph&precipitation_unit=inch&timezone=auto")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): PlaceDTO
}