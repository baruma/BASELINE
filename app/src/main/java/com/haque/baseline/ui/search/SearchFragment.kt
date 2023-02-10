package com.haque.baseline.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haque.baseline.R
import com.haque.baseline.databinding.SearchFragmentBinding

class SearchFragment : Fragment() {

//    private val searchViewModel by viewModels<SearchViewModel>()
    private lateinit var searchRecyclerAdapter: SearchRecyclerAdapter
    private lateinit var binding: SearchFragmentBinding

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
        binding.searchRecyclerview.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        searchRecyclerAdapter.updateRecyclerDate(mutableListOf())
    }
}