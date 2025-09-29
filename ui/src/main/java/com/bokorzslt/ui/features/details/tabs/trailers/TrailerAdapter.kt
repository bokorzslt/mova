package com.bokorzslt.ui.features.details.tabs.trailers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bokorzslt.domain.features.details.models.Trailer
import com.bokorzslt.ui.databinding.ItemTrailerBinding
import com.bumptech.glide.Glide

class TrailerAdapter(
    private val trailers: List<Trailer>
) : RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int =
        trailers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(trailers[position])

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
}