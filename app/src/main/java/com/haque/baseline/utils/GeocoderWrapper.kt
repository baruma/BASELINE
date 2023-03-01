package com.haque.baseline.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.gms.location.places.Place
import com.haque.baseline.data.model.PlaceData
import com.squareup.moshi.Json
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class GeocoderWrapper @Inject constructor(@ApplicationContext val context: Context) {
    private val geocoder: Geocoder = Geocoder(context)
    private var listOfPlaces: List<PlaceData> = emptyList()

//    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
        return mapAddressToPlace(result!!.first())
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