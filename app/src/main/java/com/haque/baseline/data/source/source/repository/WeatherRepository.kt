package com.haque.baseline.data.source.source.repository

import com.haque.baseline.data.model.CurrentWeatherData
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.data.model.OneCallWeatherPayload
import com.haque.baseline.domain.Resource


interface WeatherRepository {

    // need a class that compiles all the weather data together.  Get all the data from one call.
    suspend fun getCurrentWeatherData(lat: Double, long: Double): Resource<CurrentWeatherData>
    suspend fun getHourlyWeatherData(lat:Double, long: Double): Resource<HourlyWeatherData>
    suspend fun getDailyForecastedData(lat: Double, long: Double): Resource<HourlyWeatherData>
}

/*
Repositories can be used to cache data onto the machine in the form of a RoomDataBase, or, as a
Remote Data Source. Again. Repositories take form of RoomDatabases to save data onto the
 device, or, as  Room Data Sources.
 */