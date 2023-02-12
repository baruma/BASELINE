package com.haque.baseline.ui.search

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haque.baseline.R
import com.haque.baseline.data.model.PlaceData
import com.haque.baseline.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel by viewModels<SearchViewModel>()
    private lateinit var searchRecyclerAdapter: SearchRecyclerAdapter
    private lateinit var binding: SearchFragmentBinding

    private val placeObserver: Observer<List<PlaceData>> =
        Observer<List<PlaceData>> { placeData ->
            searchRecyclerAdapter.updateRecyclerDate(placeData)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchRecyclerAdapter = SearchRecyclerAdapter((mutableListOf()))

        binding.placeSearchview.setOnClickListener {
            onSearchViewTyping()
        }

        binding.searchRecyclerview.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        searchViewModel.placeData.observe(viewLifecycleOwner, placeObserver)



        CoroutineScope(Dispatchers.IO).launch {}
    }

     private fun onSearchViewTyping(): Boolean {
        binding.placeSearchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                // TODO: Set "Search On Type" functionality here to filter results
                return true
            }

            @RequiresApi(Build.VERSION_CODES.TIRAMISU)
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.searchForPlaces(query.toString())
                return true
            }
        })
        return true
    }

//    private fun searchForPlaceEntry() {
//        binding.placeSearchview.setOnQueryTextListener(
//            DebouncingQueryTextListener(
//                this@SearchFragment.lifecycle
//            ) { newText ->
//                newText?.let {
//                    if (it.isEmpty()) {
//                        searchViewModel.resetSearch()
//                    } else {
//                        CoroutineScope(IO).launch {
//                            getPlace()
//                        }
//                    }
//                }
//            }
//        )
//    }

}

// TODO: Refactor this class - put it in its own file.
internal class DebouncingQueryTextListener(
    lifecycle: Lifecycle,
    private val onDebouncingQueryTextChange: (String?) -> Unit
) : SearchView.OnQueryTextListener {
    private var debounceTime: Long = 500

    private val coroutineScope = lifecycle.coroutineScope
    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            newText?.let {
                delay(debounceTime)
                onDebouncingQueryTextChange(newText)
            }
        }
        return false
    }
}

