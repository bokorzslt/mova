package com.bokorzslt.ui.features.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bokorzslt.domain.features.details.models.Cast
import com.bokorzslt.ui.databinding.ItemCastBinding
import com.bumptech.glide.Glide

class CastAdapter(
    private val castList: List<Cast>
) : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    override fun getItemCount(): Int =
        castList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(castList[position])

    class ViewHolder(
        private val binding: ItemCastBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cast: Cast) {
            Glide.with(binding.root)
                .load(cast.profileUrl)
                .circleCrop()
                .into(binding.castCardImage)
            binding.castCardName.text = cast.name
            binding.castCardJob.text = cast.job
        }
    }
}