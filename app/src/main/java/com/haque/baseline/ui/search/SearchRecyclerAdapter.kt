package com.haque.baseline.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haque.baseline.R
import com.haque.baseline.data.model.PlaceData
import com.haque.baseline.databinding.PlaceCardBinding

class SearchRecyclerAdapter(private val dataList: MutableList<PlaceData>):
    RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder>() {

    class SearchViewHolder(private var binding: PlaceCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(placeData: PlaceData) {

            binding.placeNameTextview.text = placeData.name
            binding.executePendingBindings()
        }
    }

    fun updateRecyclerDate(newDataList: List<PlaceData>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding: PlaceCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.place_card,
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(viewHolder: SearchViewHolder, position: Int) {
        val data: PlaceData = dataList[position]
        viewHolder.bind(data)
    }
}
