package com.haque.baseline.domain

import com.haque.baseline.data.source.source.dto.OneCallWeatherPayloadDTO

interface WeatherRepository {
    // suspend fun getOneCallWeatherPayload(lat: Double, long: Double): Resource<OneCallWeatherPayloadData>
    // Should move this similarly to LocationService to follow the structure.
    suspend fun getOneCallAPIResponse(lat: Double, lng: Double): OneCallWeatherPayloadDTO
}

/*
Repositories can be used to cache data onto the machine in the form of a RoomDataBase, or, as a
Remote Data Source. Again. Repositories take form of RoomDatabases to save data onto the
 device, or, as  Room Data Sources.
*/