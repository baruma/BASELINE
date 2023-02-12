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

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun getAddressesFromEntry(place: String): List<PlaceData> {
        geocoder.getFromLocationName(
            place, 10, Geocoder.GeocodeListener { listOfAddresses ->
                listOfPlaces = listOfAddresses.map { address ->
                    mapAddressToPlace(address)
                }
            })
        return listOfPlaces
    }
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