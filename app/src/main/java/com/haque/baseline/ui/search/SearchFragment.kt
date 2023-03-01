package com.haque.baseline.ui.search

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haque.baseline.R
import com.haque.baseline.data.model.PlaceData
import com.haque.baseline.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : Fragment() {

    //    private val searchViewModel by viewModels<SearchViewModel>()
    private val searchViewModel: SearchViewModel by activityViewModels()

    private lateinit var searchRecyclerAdapter: SearchRecyclerAdapter
    private lateinit var binding: SearchFragmentBinding

    private val placeObserver: Observer<List<PlaceData>> =
        Observer<List<PlaceData>> { placeData ->
            Timber.d("Received ${placeData.size} items in observer")
            searchRecyclerAdapter.updateRecyclerDate(placeData)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)

        binding.placeSearchview.setOnClickListener {
            onSearchViewTyping()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchRecyclerAdapter =
            SearchRecyclerAdapter((mutableListOf()), object : SearchResultClickListener {
                override fun onClick(place: PlaceData) {
                    // from the viewmodel, the weather fragment can get the place's coordinates.
                    searchViewModel.selectedPlace.value = place
                    findNavController().navigate(R.id.action_search_to_weather)
                    Timber.d("Place Data: ${place.city} ")
                }

            })
        binding.searchRecyclerview.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.searchRecyclerview.adapter = searchRecyclerAdapter

        searchViewModel.placeData.observe(viewLifecycleOwner, placeObserver)
    }

    private fun onSearchViewTyping() {
        binding.placeSearchview.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                // TODO: Set "Search On Type" functionality here to filter results
                return true
            }

            // TODO:  Accomodate lower apk's.  Comment out Tiramisu when using list data from SearchVM.
            @RequiresApi(Build.VERSION_CODES.TIRAMISU)
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.searchForPlaces(query.toString())
                return true
            }
        })
    }

    private fun onPlaceViewHolderClick(place: PlaceData) {}
}