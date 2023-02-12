package com.haque.baseline.ui.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haque.baseline.data.model.PlaceData
import com.haque.baseline.utils.GeocoderWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val geocoderWrapper: GeocoderWrapper
    //    private val locationFinder: LocationFinder
): ViewModel() {
    private var searchJob: Job? = null

    private var _placeData = MutableLiveData<List<PlaceData>>()
    val placeData: LiveData<List<PlaceData>>
        get() = _placeData

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun searchForPlaces(place: String) {
        val places = geocoderWrapper.getAddressesFromEntry(place)
        _placeData.value = places
        _placeData.postValue(places)
    }

    fun resetSearch() {
        searchJob?.cancel()
    }
}