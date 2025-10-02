package com.bokorzslt.ui.features.details.tabs.trailers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bokorzslt.ui.databinding.FragmentTrailersBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class TrailersFragment : Fragment() {

    companion object {
        private const val ARG_MOVIE_ID = "movie_id"

        fun newInstance(movieId: Long): TrailersFragment =
            TrailersFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_MOVIE_ID, movieId)
                }
            }
    }

    private var _binding: FragmentTrailersBinding? = null
    private val binding get() = _binding!!

    private val movieId: Long by lazy {
        requireArguments().getLong(ARG_MOVIE_ID)
    }

    private val viewModel: TrailersViewModel by viewModel { parametersOf(movieId) }

    private val recyclerView: RecyclerView
        get() = binding.trailersList

    private lateinit var adapter: TrailerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrailersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeState()
    }

    private fun setupRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = TrailerAdapter().also {
            recyclerView.adapter = it
        }
    }

    private fun observeState() =
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is TrailersViewModel.TrailersUiState.Loading -> Unit
                        is TrailersViewModel.TrailersUiState.Success -> adapter.submitList(state.trailers)
                        is TrailersViewModel.TrailersUiState.Error -> {
                            Timber.e(state.throwable, "Could not load trailers for movie")
                            adapter.submitList(emptyList())
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