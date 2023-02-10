package com.haque.baseline.data.source.source.repository

import com.haque.baseline.data.source.source.dto.PlaceDTO
import com.haque.baseline.data.source.source.remote.PlaceApi
import com.haque.baseline.domain.PlaceRepository
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor (private val api: PlaceApi): PlaceRepository {
    override suspend fun getPlaceAPIResponse(city: String): List<PlaceDTO> {
        return api.getPlaceData(city)
    }
}