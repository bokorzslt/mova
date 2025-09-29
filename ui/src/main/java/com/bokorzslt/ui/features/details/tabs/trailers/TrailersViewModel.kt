package com.bokorzslt.ui.features.details.tabs.trailers

import com.bokorzslt.domain.dispatchers.DispatchersProvider
import com.bokorzslt.domain.features.details.models.Trailer
import com.bokorzslt.domain.features.details.usecase.GetMovieTrailersUseCase
import com.bokorzslt.ui.generic.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class TrailersViewModel(
    movieId: Long,
    private val getMovieTrailersUseCase: GetMovieTrailersUseCase,
    dispatchersProvider: DispatchersProvider
) : BaseViewModel(dispatchersProvider) {

    sealed class TrailersUiState {
        data object Loading : TrailersUiState()
        data class Success(val trailers: List<Trailer>) : TrailersUiState()
        data class Error(val throwable: Throwable) : TrailersUiState()
    }

    private val mutableState = MutableStateFlow<TrailersUiState>(TrailersUiState.Loading)
    val state = mutableState.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        mutableState.value = TrailersUiState.Error(throwable)
    }

    init {
        Timber.d("Load trailers for movie: $movieId")
        loadMovieTrailers(movieId)
    }

    private fun loadMovieTrailers(movieId: Long) =
        launchOnIO(errorHandler) {
            mutableState.value = TrailersUiState.Success(getMovieTrailersUseCase(movieId))
        }
}