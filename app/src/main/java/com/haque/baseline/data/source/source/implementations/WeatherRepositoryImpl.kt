package com.haque.baseline.data.source.source.implementations

import com.haque.baseline.data.source.source.dto.OneCallWeatherPayloadDTO
import com.haque.baseline.data.source.source.remote.WeatherApi
import com.haque.baseline.domain.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi) : WeatherRepository {
    override suspend fun getOneCallAPIResponse(lat: Float, lng: Float): OneCallWeatherPayloadDTO {
        return api.getWeatherData(lat, lng)
    }
}