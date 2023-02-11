package com.haque.baseline.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haque.baseline.data.mappers.toOneCallWeatherPayloadData
import com.haque.baseline.data.mappers.toPlace
import com.haque.baseline.data.model.PlaceData
import com.haque.baseline.domain.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: PlaceRepository): ViewModel() {
    private var searchJob: Job? = null

    private var _placeData = MutableLiveData<List<PlaceData>>()
    val placeData: LiveData<List<PlaceData>>
        get() = _placeData

    suspend fun getPlace() {
        val result = repository.getPlaceAPIResponse("Boston")

        val places = result.map {
            placeDTO ->
            placeDTO.toPlace()
        }
        _placeData.postValue(places)
    }

    fun resetSearch() {
        searchJob?.cancel()
    }
}