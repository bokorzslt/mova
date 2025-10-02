package com.bokorzslt.ui.features.details.tabs.comments

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
import com.bokorzslt.ui.databinding.FragmentCommentsBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class CommentsFragment : Fragment() {

    companion object {
        private const val ARG_MOVIE_ID = "movie_id"

        fun newInstance(movieId: Long): CommentsFragment =
            CommentsFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_MOVIE_ID, movieId)
                }
            }
    }

    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!

    private val movieId: Long by lazy {
        requireArguments().getLong(ARG_MOVIE_ID)
    }

    private val viewModel: CommentsViewModel by viewModel { parametersOf(movieId) }

    private lateinit var adapter: ReviewAdapter

    private val recyclerView: RecyclerView
        get() = binding.reviewsList

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentsBinding.inflate(inflater)
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
        adapter = ReviewAdapter().also {
            recyclerView.adapter = it
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is CommentsViewModel.CommentsUiState.Loading -> Unit
                        is CommentsViewModel.CommentsUiState.Success -> adapter.submitList(state.reviews)
                        is CommentsViewModel.CommentsUiState.Error -> {
                            Timber.e(state.throwable, "Could not load reviews for movie")
                            adapter.submitList(emptyList())
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