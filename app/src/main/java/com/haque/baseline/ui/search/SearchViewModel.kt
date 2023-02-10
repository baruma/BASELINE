package com.haque.baseline.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haque.baseline.data.model.PlaceData
import com.haque.baseline.domain.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: PlaceRepository): ViewModel() {
    lateinit var placeDataResponse: PlaceData

    private var _placeData = MutableLiveData<PlaceData>()
    val placeData: LiveData<PlaceData>
        get() = _placeData



}