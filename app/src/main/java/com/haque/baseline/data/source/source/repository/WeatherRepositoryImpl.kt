package com.haque.baseline.data.source.source.repository

import com.haque.baseline.data.model.CurrentWeatherData
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.data.source.source.remote.WeatherApi
import com.haque.baseline.domain.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor (private val api: WeatherApi): WeatherRepository {
    override suspend fun getCurrentWeatherData(lat: Double, long: Double): Resource<CurrentWeatherData> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toCurrentWeatherData()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getHourlyWeatherData(
        lat: Double,
        long: Double
    ): Resource<HourlyWeatherData> {
        TODO("Not yet implemented")
    }

    override suspend fun getDailyForecastedData(
        lat: Double,
        long: Double
    ): Resource<HourlyWeatherData> {
        TODO("Not yet implemented")
    }

}