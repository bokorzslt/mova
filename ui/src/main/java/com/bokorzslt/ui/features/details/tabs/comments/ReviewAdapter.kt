package com.bokorzslt.ui.features.details.tabs.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bokorzslt.domain.features.details.models.Review
import com.bokorzslt.ui.R
import com.bokorzslt.ui.databinding.ItemReviewBinding
import com.bumptech.glide.Glide

class ReviewAdapter : ListAdapter<Review, ReviewAdapter.ViewHolder>(REVIEW_DIFF) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) = holder.bind(currentList[position])

    class ViewHolder(
        private val binding: ItemReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(review: Review) {
            Glide.with(binding.root)
                .load(review.avatarUrl)
                .error(R.drawable.review_card_image_placeholder)
                .placeholder(R.drawable.review_card_image_placeholder)
                .circleCrop()
                .into(binding.reviewCardImage)
            binding.reviewCardAuthorName.text = review.authorName
            binding.reviewCardContent.text = review.content
            binding.reviewCardCreatedAt.text = review.createdAt
        }
    }

    companion object {
        private val REVIEW_DIFF = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(
                oldItem: Review,
                newItem: Review
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Review,
                newItem: Review
            ): Boolean =
                oldItem == newItem
        }
    }
}