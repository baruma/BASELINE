package com.haque.baseline.data.source.source.remote

import com.haque.baseline.BuildConfig
import com.haque.baseline.data.source.source.dto.PlaceDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PlaceApi {
    @Headers("X-Api-Key: ${BuildConfig.API_NINJA_GEOCODING_KEY}")
    @GET("geocoding?city=")
    suspend fun getPlaceData(
        @Query("city") city: String
    ): List<PlaceDTO>
}