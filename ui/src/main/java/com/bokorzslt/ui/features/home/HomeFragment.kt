package com.bokorzslt.ui.features.home

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
import com.bokorzslt.domain.features.home.models.HomePageModule
import com.bokorzslt.ui.databinding.FragmentHomeBinding
import com.bokorzslt.ui.features.home.adapter.HomeAdapter
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

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is HomeViewModel.HomeUiState.Loading -> {
                            Timber.d("Home page loading")
                            loadingSpinner.show()
                        }

                        is HomeViewModel.HomeUiState.Success -> {
                            Timber.d("Home page loaded")
                            loadingSpinner.hide()
                            setupRecyclerView(state.modules)
                        }

                        is HomeViewModel.HomeUiState.Error -> {
                            Timber.d("Error loading home page")
                            loadingSpinner.hide()
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView(modules: List<HomePageModule>) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = HomeAdapter(modules)
    }
}