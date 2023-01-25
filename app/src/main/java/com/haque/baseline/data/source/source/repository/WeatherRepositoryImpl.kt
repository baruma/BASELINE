package com.haque.baseline.data.source.source.repository

import com.haque.baseline.data.source.source.remote.WeatherApi
import com.haque.baseline.domain.Resource
import javax.inject.Inject

// Since the Implementation class is doing the heavy lifting, we go ahead and feed it our api.
class WeatherRepositoryImpl @Inject constructor (private val api: WeatherApi): WeatherRepository {
    override suspend fun testAPIResponse(lat: Double, long: Double): Resource<String> {
        return try {
            Resource.Success(
                data = api.getWeatherData(lat, long).toString()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(
                "error", e.message
            )
        }
    }
}