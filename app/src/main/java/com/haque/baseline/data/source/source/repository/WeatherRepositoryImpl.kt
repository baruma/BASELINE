package com.haque.baseline.data.source.source.repository

import com.haque.baseline.data.source.source.dto.OneCallWeatherPayloadDTO
import com.haque.baseline.data.source.source.remote.WeatherApi
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor (private val api: WeatherApi): WeatherRepository {
    override suspend fun testAPIResponse(lat: Double, lng: Double): OneCallWeatherPayloadDTO {
        return api.getWeatherData(lat, lng)
        // DO NOT keep try catches in network calls.  Try Catch's are slow
        // and you should not slow down processes that require frequent or semi - frequent network calls
    }
}