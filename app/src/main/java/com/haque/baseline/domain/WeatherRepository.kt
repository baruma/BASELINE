package com.haque.baseline.domain

import com.haque.baseline.data.source.source.dto.OneCallWeatherPayloadDTO

interface WeatherRepository {
    suspend fun getOneCallAPIResponse(lat: Float, lng: Float): OneCallWeatherPayloadDTO
}