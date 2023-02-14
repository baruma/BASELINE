package com.haque.baseline.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haque.baseline.R
import com.haque.baseline.data.model.PlaceData
import com.haque.baseline.databinding.PlaceCardBinding
import timber.log.Timber


class SearchRecyclerAdapter(
    private val dataList: MutableList<PlaceData>, private val searchResultClickListener: SearchResultClickListener) :
    RecyclerView.Adapter<SearchRecyclerAdapter.PlaceViewHolder>() {

    /*
 writing a class within a class alone, will not grant the nested class permission to variables
 of its upper nested class.  Classes nested like this are static.  A solution to this
 is to declare it as an "inner" class, so you get access to the upper class' properties.

 However, declaring inner classes can tightly couple them to the class they're inside.  This is
 not an issue in this app, but it would be an issue if you wanted to remove this ViewHolder from
 here to reuse in other views.
 */
    inner class PlaceViewHolder(private var binding: PlaceCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(placeData: PlaceData) {
            binding.executePendingBindings()
            binding.placeNameTextview.text = placeData.city
        }
    }

    fun updateRecyclerDate(newDataList: List<PlaceData>) {
        dataList.clear()
        dataList.addAll(newDataList)
        Timber.d("Refreshing adapter with ${newDataList.size} items")
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
        return dataList.count()
    }

    override fun onBindViewHolder(viewHolder: PlaceViewHolder, position: Int) {
        val data: PlaceData = dataList[position]
        viewHolder.itemView.setOnClickListener {
            searchResultClickListener.onClick(data)
        }
        viewHolder.bind(data)
    }
}

interface SearchResultClickListener {
    fun onClick(place: PlaceData)
}