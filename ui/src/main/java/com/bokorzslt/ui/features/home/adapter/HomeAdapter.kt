package com.bokorzslt.ui.features.home.adapter

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bokorzslt.domain.features.details.models.MovieDetails
import com.bokorzslt.domain.features.home.models.HomePageModule
import com.bokorzslt.domain.features.home.models.Movie
import com.bokorzslt.domain.features.home.models.Stripe
import com.bokorzslt.ui.databinding.ItemGalleryBinding
import com.bokorzslt.ui.databinding.ItemStripeBinding
import com.bokorzslt.ui.utils.StringUtils
import com.bumptech.glide.Glide

typealias MovieClickListener = (Movie) -> Unit

class HomeAdapter(
    private val movieClickListener: MovieClickListener,
    private val searchClickListener: OnClickListener,
    private val notificationClickListener: OnClickListener
) : ListAdapter<HomePageModule, RecyclerView.ViewHolder>(HOME_PAGE_DIFF) {

    private val sharedViewPool = RecyclerView.RecycledViewPool()

    override fun getItemViewType(position: Int): Int =
        when (currentList[position]) {
            is HomePageModule.GalleryModule -> GALLERY_VIEW_TYPE
            is HomePageModule.StripeModule -> STRIPE_VIEW_TYPE
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            GALLERY_VIEW_TYPE -> GalleryViewHolder(
                ItemGalleryBinding.inflate(
                    inflater,
                    parent,
                    false
                )

            )

            STRIPE_VIEW_TYPE -> StripeViewHolder(
                ItemStripeBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (val module = currentList[position]) {
            is HomePageModule.GalleryModule -> (holder as GalleryViewHolder).bind(module.movie)
            is HomePageModule.StripeModule -> (holder as StripeViewHolder).bind(module.stripe)
        }

    inner class GalleryViewHolder(
        private val binding: ItemGalleryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.gallerySearchIcon.setOnClickListener(searchClickListener)
            binding.galleryNotificationIcon.setOnClickListener(notificationClickListener)
        }

        fun bind(movie: MovieDetails) {
            Glide.with(binding.root)
                .load(movie.backdropUrl)
                .into(binding.galleryImage)
            binding.galleryTitle.text = movie.title
            binding.galleryDescription.text =
                movie.genres.joinToString(separator = StringUtils.COMMA_SEPARATOR_WITH_SPACE)
        }
    }

    inner class StripeViewHolder(
        private val binding: ItemStripeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val movieAdapter: MovieAdapter = MovieAdapter(movieClickListener)

        init {
            with(binding) {
                stripeRecyclerView.setHasFixedSize(true)
                stripeRecyclerView.layoutManager =
                    LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
                stripeRecyclerView.setRecycledViewPool(sharedViewPool)
                stripeRecyclerView.adapter = movieAdapter
            }
        }

        fun bind(stripe: Stripe) {
            binding.stripeTitle.text = stripe.title
            movieAdapter.submitList(stripe.movies)
        }
    }

    companion object {
        private const val GALLERY_VIEW_TYPE = 0
        private const val STRIPE_VIEW_TYPE = 1

        val HOME_PAGE_DIFF = object : DiffUtil.ItemCallback<HomePageModule>() {
            override fun areItemsTheSame(
                oldItem: HomePageModule,
                newItem: HomePageModule
            ): Boolean {
                return when {
                    oldItem is HomePageModule.GalleryModule &&
                            newItem is HomePageModule.GalleryModule ->
                        oldItem.movie.id == newItem.movie.id

                    oldItem is HomePageModule.StripeModule &&
                            newItem is HomePageModule.StripeModule ->
                        oldItem.stripe.title == newItem.stripe.title &&
                                oldItem.stripe.movies.map { it.id } == newItem.stripe.movies.map { it.id }

                    else -> false
                }
            }

            override fun areContentsTheSame(
                oldItem: HomePageModule,
                newItem: HomePageModule
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}