package com.nureddinelmas.myplacetesting.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.nureddinelmas.myplacetesting.R
import com.nureddinelmas.myplacetesting.model.Places
import javax.inject.Inject

class PlaceRecyclerAdapter @Inject constructor(private val glide: RequestManager) : RecyclerView.Adapter<PlaceRecyclerAdapter.PlaceViewHolder>(){

    class PlaceViewHolder(var view : View) : RecyclerView.ViewHolder(view)

    // Diffutil recycler view gerekli olan yerlerini guncelleyen bir obje.
    private val diffUtil = object : DiffUtil.ItemCallback<Places>() {
        override fun areItemsTheSame(oldItem: Places, newItem: Places): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Places, newItem: Places): Boolean {
           return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var place : List<Places>
      get() = recyclerListDiffer.currentList
      set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_places,parent, false)
        return PlaceViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.placeRowImageView)
        val nameText = holder.itemView.findViewById<TextView>(R.id.placeRowNameText)
        val cityName = holder.itemView.findViewById<TextView>(R.id.placeRowCityNameText)
        val visitYear = holder.itemView.findViewById<TextView>(R.id.placeRowVisitYearText)

        val place = place[position]
        holder.itemView.apply {
            nameText.text = "Name : ${place.name}"
            cityName.text = "City Name : ${place.cityName}"
            visitYear.text = "Visit Year : ${place.visitYear}"
            glide.load(place.imageUrl).into(imageView)

        }
    }

    override fun getItemCount(): Int {
        return place.size
    }
}