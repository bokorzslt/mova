package com.bokorzslt.ui.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bokorzslt.domain.features.home.models.Movie
import com.bokorzslt.ui.R
import com.bokorzslt.ui.databinding.ItemMovieBinding
import com.bokorzslt.ui.utils.formatAsRating
import com.bumptech.glide.Glide

class MovieAdapter(
    private val movies: List<Movie>,
    private val movieClickListener: MovieClickListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])

    override fun getItemCount() = movies.size

    inner class ViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                movieClickListener(movies[adapterPosition])
            }
        }

        fun bind(movie: Movie) {
            Glide.with(binding.root)
                .load(movie.posterUrl)
                .placeholder(R.drawable.movie_card_image_placeholder)
                .error(R.drawable.movie_card_image_placeholder)
                .into(binding.movieCardImage)
            binding.movieCardRating.text = movie.rating.formatAsRating()
        }
    }
}
