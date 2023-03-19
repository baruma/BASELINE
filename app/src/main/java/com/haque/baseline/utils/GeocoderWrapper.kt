package com.haque.baseline.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import androidx.annotation.RequiresApi
import com.haque.baseline.data.model.PlaceData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GeocoderWrapper @Inject constructor(@ApplicationContext val context: Context) {
    private val geocoder: Geocoder = Geocoder(context)
    private var listOfPlaces: List<PlaceData> = emptyList()

    // I recommend putting maxResults as part of an argument rather than hardcoded as 5
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun getAddressesFromEntry(place: String): List<PlaceData> {
        geocoder.getFromLocationName(
            place, 5, Geocoder.GeocodeListener { listOfAddresses ->
                listOfPlaces = listOfAddresses.map { address ->
                    mapAddressToPlace(address)
                }
            })
        return listOfPlaces
    }

    fun getCurrentLocation(lat: Double, lon: Double): PlaceData {
        val result = geocoder.getFromLocation(lat, lon, 1)
        return mapAddressToPlace(result!!.first())  // rewrite this without !!
    }

    fun mapAddressToPlace(address: Address): PlaceData {
        return PlaceData(
            address.locality,
            address.latitude.toFloat(),
            address.longitude.toFloat(),
            address.countryName,
            address.adminArea
        )
    }
}