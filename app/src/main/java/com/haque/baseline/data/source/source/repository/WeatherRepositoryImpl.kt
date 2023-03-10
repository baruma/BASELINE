package com.haque.baseline.data.source.source.repository

import com.haque.baseline.data.source.source.dto.OneCallWeatherPayloadDTO
import com.haque.baseline.data.source.source.remote.WeatherApi
import com.haque.baseline.domain.WeatherRepository
import javax.inject.Inject

/*
Hint
DO NOT keep try catches in network calls.  Try Catch's are slow, and can slow down processes
that require frequent or semi - frequent network calls.
 */

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi) : WeatherRepository {
    override suspend fun getOneCallAPIResponse(lat: Float, lng: Float): OneCallWeatherPayloadDTO {
        return api.getWeatherData(lat, lng)
    }
}