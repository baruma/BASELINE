package com.haque.baseline.data.source.source.repository

import com.haque.baseline.data.source.source.remote.WeatherApi
import com.haque.baseline.domain.Resource
import okhttp3.ResponseBody
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor (private val api: WeatherApi): WeatherRepository {
    override suspend fun testAPIResponse(lat: Double, lng: Double): ResponseBody {
        return api.getWeatherData(lat, lng)
        // DO NOT keep try catches in network calls.  Try Catch's are slow
        // and you should not slow down processes that require frequent or semi - frequent network calls
//        return try {
//            Resource.Success(
//                data = api.getWeatherData(lat, long)
//
//            )
//        } catch(e: Exception) {
////            e.printStackTrace()
//            Resource.Error()
//        }
    }
}

