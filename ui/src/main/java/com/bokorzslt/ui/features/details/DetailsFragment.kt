package com.bokorzslt.ui.features.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bokorzslt.domain.features.details.models.MovieDetails
import com.bokorzslt.ui.MainActivity
import com.bokorzslt.ui.R
import com.bokorzslt.ui.databinding.FragmentDetailsBinding
import com.bokorzslt.ui.features.details.adapter.CastAdapter
import com.bokorzslt.ui.features.details.tabs.CommentsFragment
import com.bokorzslt.ui.features.details.tabs.SimilarMoviesFragment
import com.bokorzslt.ui.features.details.tabs.trailers.TrailersFragment
import com.bokorzslt.ui.utils.StringUtils
import com.bokorzslt.ui.utils.formatAsRating
import com.bokorzslt.ui.utils.show
import com.bumptech.glide.Glide
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val loadingSpinner: CircularProgressIndicator
        get() = binding.detailsLoadingSpinner

    private val coverImageView: ImageView
        get() = binding.detailsImage

    private val titleTextView: TextView
        get() = binding.detailsTitle

    private val ratingImageView: ImageView
        get() = binding.detailsRatingImage

    private val ratingTextView: TextView
        get() = binding.detailsRatingText

    private val arrowImageView: ImageView
        get() = binding.detailsArrowImage

    private val releaseYearTextView: TextView
        get() = binding.detailsReleaseYearText

    private val playButton: TextView
        get() = binding.detailsPlayButton

    private val downloadButton: TextView
        get() = binding.detailsDownloadButton

    private val genresTextView: TextView
        get() = binding.detailsGenres

    private val descriptionTextView: TextView
        get() = binding.detailsDescription

    private val castRecyclerView: RecyclerView
        get() = binding.detailsCastList

    private val tabLayout: TabLayout
        get() = binding.detailsTabLayout

    private val viewPager: ViewPager2
        get() = binding.detailsViewPager

    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel: DetailsViewModel by viewModel { parametersOf(args.movieId) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.hideBottomNavigation()
        observeState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        (activity as? MainActivity)?.showBottomNavigation()
    }

    private fun observeState() =
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        DetailsViewModel.DetailsUiState.Loading -> {
                            Timber.d("Detail page loading")
                            loadingSpinner.show()
                        }

                        is DetailsViewModel.DetailsUiState.Success -> {
                            Timber.d("Detail page loaded")
                            loadingSpinner.hide()
                            showDetails(state.movie)
                        }

                        is DetailsViewModel.DetailsUiState.Error -> {
                            Timber.e("Failed to load movie: ${state.throwable}")
                        }
                    }
                }
            }
        }

    private fun showDetails(movie: MovieDetails) {
        loadImage(movie)
        showTitle(movie)
        showRating(movie)
        showButtons()
        showReleaseYear(movie)
        showGenres(movie)
        showDescription(movie)
        showCastList(movie)
        setupViewPager()
        setupTabLayout()
    }

    private fun loadImage(movie: MovieDetails) =
        Glide.with(this)
            .load(movie.backdropUrl)
            .placeholder(R.drawable.movie_card_image_placeholder)
            .error(R.drawable.movie_card_image_placeholder)
            .into(coverImageView)

    private fun showTitle(movie: MovieDetails) {
        titleTextView.text = movie.title
    }

    private fun showRating(movie: MovieDetails) {
        ratingImageView.show()
        ratingTextView.text = movie.rating.formatAsRating()
        arrowImageView.show()
    }

    private fun showButtons() {
        playButton.show()
        downloadButton.show()
    }

    private fun showReleaseYear(movie: MovieDetails) {
        releaseYearTextView.text = movie.releaseYear.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun showGenres(movie: MovieDetails) {
        genresTextView.text = "${resources.getString(R.string.details_genre_label)} ${
            movie.genres.joinToString(separator = StringUtils.COMMA_SEPARATOR_WITH_SPACE)
        }"
    }

    private fun showDescription(movie: MovieDetails) {
        descriptionTextView.text = movie.description
    }

    private fun showCastList(movie: MovieDetails) {
        castRecyclerView.setHasFixedSize(true)
        castRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        castRecyclerView.adapter = CastAdapter(movie.castList)
    }

    private fun setupViewPager() {
        val fragments = listOf(
            TrailersFragment.newInstance(args.movieId),
            SimilarMoviesFragment(),
            CommentsFragment()
        )

        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = fragments.size
            override fun createFragment(position: Int): Fragment = fragments[position]
        }
    }

    private fun setupTabLayout() {
        val context = context ?: return

        val tabs = listOf(
            context.getString(R.string.details_trailers_tab),
            context.getString(R.string.details_more_like_this_tab),
            context.getString(R.string.details_comments_tab)
        )

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabs[position]
        }.attach()
    }
}