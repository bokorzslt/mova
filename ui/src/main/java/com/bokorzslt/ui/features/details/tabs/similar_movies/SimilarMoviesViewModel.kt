package com.bokorzslt.ui.features.details.tabs.similar_movies

import com.bokorzslt.domain.dispatchers.DispatchersProvider
import com.bokorzslt.domain.features.details.usecase.GetSimilarMoviesUseCase
import com.bokorzslt.domain.features.home.models.Movie
import com.bokorzslt.ui.generic.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SimilarMoviesViewModel(
    movieId: Long,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    dispatchersProvider: DispatchersProvider
) : BaseViewModel(dispatchersProvider) {

    sealed class SimilarMoviesUiState {
        data object Loading : SimilarMoviesUiState()
        data class Success(val movies: List<Movie>) : SimilarMoviesUiState()
        data class Error(val throwable: Throwable) : SimilarMoviesUiState()
    }

    private val mutableState = MutableStateFlow<SimilarMoviesUiState>(SimilarMoviesUiState.Loading)
    val state = mutableState.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        mutableState.value = SimilarMoviesUiState.Error(throwable)
    }

    init {
        loadSimilarMovies(movieId)
    }

    private fun loadSimilarMovies(movieId: Long) {
        launchOnIO(errorHandler) {
            mutableState.value = SimilarMoviesUiState.Success(getSimilarMoviesUseCase(movieId))
        }
    }
}