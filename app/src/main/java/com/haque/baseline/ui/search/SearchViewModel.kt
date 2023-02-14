package com.haque.baseline.ui.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haque.baseline.data.model.PlaceData
import com.haque.baseline.utils.GeocoderWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val geocoderWrapper: GeocoderWrapper): ViewModel() {

    private var _placeData = MutableLiveData<List<PlaceData>>()
    val placeData: LiveData<List<PlaceData>>
        get() = _placeData

    var selectedPlace = MutableLiveData<PlaceData>()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun searchForPlaces(place: String) {
        val places = geocoderWrapper.getAddressesFromEntry(place)
//        val places = listOf<PlaceData>(PlaceData("San Francisco", 0.0f, 0.0f, "USA", "CA"),
//            PlaceData("San Berdino", 0.0f, 0.0f, "USA", "CA"))
        Timber.d("ViewMode.searchForPlaces returns with  ${places.size} items")
        _placeData.value = places
        _placeData.postValue(places)
    }
}