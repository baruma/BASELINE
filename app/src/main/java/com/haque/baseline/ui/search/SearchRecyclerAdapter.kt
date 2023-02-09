package com.haque.baseline.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haque.baseline.R
import com.haque.baseline.data.model.PlaceData
import com.haque.baseline.databinding.PlaceCardBinding

class SearchRecyclerAdapter(private val dataList: MutableList<PlaceData>):
    RecyclerView.Adapter<SearchRecyclerAdapter.PlaceViewHolder>() {

    class PlaceViewHolder(private var binding: PlaceCardBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding: PlaceCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.place_card,
            parent,
            false
        )
        return PlaceViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(viewHolder: PlaceViewHolder, position: Int) {
        val data: PlaceData = dataList[position]
        viewHolder.bind(data)
    }
}
