package com.haque.baseline.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haque.baseline.data.model.PlaceData
import kotlinx.coroutines.Job
class SearchViewModel: ViewModel() {
    private var searchJob: Job? = null

    private var _placeData = MutableLiveData<List<PlaceData>>()
    val placeData: LiveData<List<PlaceData>>
        get() = _placeData

    fun resetSearch() {
        searchJob?.cancel()
    }
}