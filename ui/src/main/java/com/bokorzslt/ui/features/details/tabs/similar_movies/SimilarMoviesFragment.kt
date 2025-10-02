package com.bokorzslt.ui.features.details.tabs.similar_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bokorzslt.ui.R
import com.bokorzslt.ui.databinding.FragmentSimilarMoviesBinding
import com.bokorzslt.ui.features.home.adapter.MovieAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class SimilarMoviesFragment : Fragment() {

    companion object {
        private const val ARG_MOVIE_ID = "movie_id"
        private const val NUMBER_OF_COLUMNS = 2

        fun newInstance(movieId: Long): SimilarMoviesFragment =
            SimilarMoviesFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_MOVIE_ID, movieId)
                }
            }
    }


    private var _binding: FragmentSimilarMoviesBinding? = null
    private val binding get() = _binding!!

    private val movieId: Long by lazy {
        requireArguments().getLong(ARG_MOVIE_ID)
    }

    private val viewModel: SimilarMoviesViewModel by viewModel { parametersOf(movieId) }

    private lateinit var movieAdapter: MovieAdapter

    private val recyclerView: RecyclerView
        get() = binding.similarMoviesList

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimilarMoviesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeState()
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter { movie ->
            // Handle movie click - can be implemented later if needed
        }
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), NUMBER_OF_COLUMNS)
            adapter = movieAdapter
            val verticalSpacing = resources.getDimension(R.dimen.margin_12)
            addItemDecoration(VerticalSpacingItemDecoration(verticalSpacing.toInt()))
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is SimilarMoviesViewModel.SimilarMoviesUiState.Loading -> Unit
                        is SimilarMoviesViewModel.SimilarMoviesUiState.Success -> movieAdapter.submitList(
                            state.movies
                        )

                        is SimilarMoviesViewModel.SimilarMoviesUiState.Error -> {
                            Timber.e(state.throwable, "Could not load similar movies")
                            movieAdapter.submitList(emptyList())
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView.adapter = null
        _binding = null
    }
}