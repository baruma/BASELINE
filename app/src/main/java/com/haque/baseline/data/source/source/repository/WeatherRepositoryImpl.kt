package com.haque.baseline.data.source.source.repository

import com.haque.baseline.data.mappers.toOneCallWeatherPayloadData
import com.haque.baseline.data.model.OneCallWeatherPayloadData
import com.haque.baseline.data.source.source.dto.OneCallWeatherPayloadDTO
import com.haque.baseline.data.source.source.remote.WeatherApi
import com.haque.baseline.domain.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor (private val api: WeatherApi): Resource<OneCallWeatherPayloadDfTO> {

    override suspend fun getOneCallWeatherPayload (
        lat: Double,
        long: Double
    ): Resource<OneCallWeatherPayloadData> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toOneCallWeatherPayloadData()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }    }

}