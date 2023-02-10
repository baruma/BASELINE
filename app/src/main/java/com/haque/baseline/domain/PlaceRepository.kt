package com.haque.baseline.domain

import com.haque.baseline.data.source.source.dto.PlaceDTO

interface PlaceRepository {
    suspend fun getPlaceAPIResponse(city: String): List<PlaceDTO>
}