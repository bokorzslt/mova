package com.bokorzslt.ui.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bokorzslt.domain.features.home.models.Movie
import com.bokorzslt.ui.databinding.FragmentHomeBinding
import com.bokorzslt.ui.features.home.adapter.HomeAdapter
import com.bokorzslt.ui.generic.views.ErrorView
import com.bokorzslt.ui.utils.hide
import com.bokorzslt.ui.utils.show
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val loadingSpinner: CircularProgressIndicator
        get() = binding.homeLoadingSpinner

    private val recyclerView: RecyclerView
        get() = binding.homeRecyclerView

    private val errorView: ErrorView
        get() = binding.errorView

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is HomeViewModel.HomeUiState.Loading -> {
                            Timber.d("Home page loading")
                            loadingSpinner.show()
                            recyclerView.hide()
                            errorView.hide()
                        }

                        is HomeViewModel.HomeUiState.Success -> {
                            Timber.d("Home page loaded")
                            loadingSpinner.hide()
                            errorView.hide()
                            recyclerView.show()
                            adapter.submitList(state.modules)
                        }

                        is HomeViewModel.HomeUiState.Error -> {
                            Timber.e(state.throwable, "Error loading home page: ${state.throwable}")
                            loadingSpinner.hide()
                            errorView.show()
                            recyclerView.hide()
                            adapter.submitList(emptyList())
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        adapter = HomeAdapter(
            movieClickListener = { navigateToDetails(it) },
            searchClickListener = { navigateToSearch() },
            notificationClickListener = { navigateToNotifications() }
        ).also {
            recyclerView.adapter = it
        }
    }

    private fun navigateToDetails(movie: Movie) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie.id)
        findNavController().navigate(action)
    }

    private fun navigateToSearch() {
        Toast.makeText(requireContext(), "Search clicked", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToNotifications() {
        Toast.makeText(requireContext(), "Notifications clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}