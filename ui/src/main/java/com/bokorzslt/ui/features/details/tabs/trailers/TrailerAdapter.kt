package com.bokorzslt.ui.features.details.tabs.trailers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bokorzslt.domain.features.details.models.Trailer
import com.bokorzslt.ui.databinding.ItemTrailerBinding
import com.bumptech.glide.Glide

class TrailerAdapter : ListAdapter<Trailer, TrailerAdapter.ViewHolder>(TRAILER_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(currentList[position])

    class ViewHolder(
        private val binding: ItemTrailerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(trailer: Trailer) {
            Glide.with(binding.root)
                .load(trailer.imageUrl)
                .into(binding.trailerImage)
            binding.trailerTitle.text = trailer.name
        }
    }

    companion object {
        private val TRAILER_DIFF = object : DiffUtil.ItemCallback<Trailer>() {
            override fun areItemsTheSame(
                oldItem: Trailer,
                newItem: Trailer
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Trailer,
                newItem: Trailer
            ): Boolean =
                oldItem == newItem
        }
    }
}