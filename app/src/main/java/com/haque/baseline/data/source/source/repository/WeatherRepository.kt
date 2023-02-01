package com.haque.baseline.data.source.source.repository

import com.haque.baseline.data.model.OneCallWeatherPayloadData
import com.haque.baseline.data.source.source.dto.OneCallWeatherPayloadDTO
import com.haque.baseline.domain.Resource
import okhttp3.ResponseBody


interface WeatherRepository {
    // suspend fun getOneCallWeatherPayload(lat: Double, long: Double): Resource<OneCallWeatherPayloadData>
    suspend fun testAPIResponse(lat: Double, lng: Double): OneCallWeatherPayloadDTO
}

/*
Repositories can be used to cache data onto the machine in the form of a RoomDataBase, or, as a
Remote Data Source. Again. Repositories take form of RoomDatabases to save data onto the
 device, or, as  Room Data Sources.
*/