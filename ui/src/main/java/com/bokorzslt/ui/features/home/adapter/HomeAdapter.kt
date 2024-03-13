package com.bokorzslt.ui.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bokorzslt.domain.features.home.models.HomePageModule
import com.bokorzslt.domain.features.home.models.Movie
import com.bokorzslt.domain.features.home.models.Stripe
import com.bokorzslt.ui.databinding.ItemGalleryBinding
import com.bokorzslt.ui.databinding.ItemStripeBinding
import com.bokorzslt.ui.utils.StringUtils
import com.bumptech.glide.Glide

class HomeAdapter(
    private val modules: List<HomePageModule>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int =
        when (modules[position]) {
            is HomePageModule.GalleryModule -> GALLERY_VIEW_TYPE
            is HomePageModule.StripeModule -> STRIPE_VIEW_TYPE
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            GALLERY_VIEW_TYPE -> GalleryViewHolder(
                ItemGalleryBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ),
                    parent,
                    false
                )
            )

            STRIPE_VIEW_TYPE -> StripeViewHolder(
                ItemStripeBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("Unknown view type")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (val module = modules[position]) {
            is HomePageModule.GalleryModule -> (holder as GalleryViewHolder).bind(module.movie)
            is HomePageModule.StripeModule -> (holder as StripeViewHolder).bind(module.stripe)
            else -> throw IllegalArgumentException("Unknown view holder type")
        }

    override fun getItemCount(): Int = modules.size

    class GalleryViewHolder(
        private val binding: ItemGalleryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            Glide.with(binding.root)
                .load(movie.backdropPath)
                .into(binding.galleryImage)
            binding.galleryTitle.text = movie.title
            binding.galleryDescription.text =
                movie.genres.joinToString(separator = StringUtils.COMMA_SEPARATOR_WITH_SPACE)
        }
    }

    class StripeViewHolder(
        private val binding: ItemStripeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.stripeRecyclerView.setHasFixedSize(true)
        }

        fun bind(stripe: Stripe) {
            binding.stripeTitle.text = stripe.title
            binding.stripeRecyclerView.layoutManager =
                LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
            binding.stripeRecyclerView.adapter = MovieAdapter(stripe.movies)
        }
    }

    companion object {
        private const val GALLERY_VIEW_TYPE = 0
        private const val STRIPE_VIEW_TYPE = 1
    }
}