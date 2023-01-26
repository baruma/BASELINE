package com.haque.baseline.data.source.source.repository

import com.haque.baseline.data.source.source.remote.WeatherApi
import com.haque.baseline.domain.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor (private val api: WeatherApi): WeatherRepository {
    override suspend fun testAPIResponse(lat: Double, long: Double): Resource<String> {
        return try {
            Resource.Success(
                data = api.getWeatherData(lat, long)

            )
        } catch(e: Exception) {
//            e.printStackTrace()
            Resource.Error(
                "hahahahahahah its null", e.message
            )
        }
    }
}

// main activity - viewmodel - weather repository - weatherrepositoryimpl (has success and error cases) after calling api - weather api
// I don't see the builder being used anywhere in my flow.