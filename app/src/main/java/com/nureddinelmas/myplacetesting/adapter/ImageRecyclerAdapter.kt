package com.nureddinelmas.myplacetesting.adapter

import android.annotation.SuppressLint
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.nureddinelmas.myplacetesting.R
import javax.inject.Inject

class ImageRecyclerAdapter @Inject constructor(val glide : RequestManager) : RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var onItemClickListener : ((String) -> Unit) ? = null

    var diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerImage = AsyncListDiffer(this, diffUtil)

    var images : List<String>
             get() = recyclerImage.currentList
             set(value) = recyclerImage.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.image_place, parent, false)
        return ImageViewHolder(view)
    }

    fun setOnItemClickListener(listener : (String) -> Unit){
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        val image = holder.itemView.findViewById<ImageView>(R.id.imageViewRecycler)

        holder.itemView.apply {
            glide.load(images[position]).into(image)
            setOnClickListener {
                onItemClickListener?.let {
                    it(images[position])
                }
            }
        }

    }

    override fun getItemCount(): Int {
       return images.size
    }
}