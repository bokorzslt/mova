package com.bokorzslt.ui.features.details

import com.bokorzslt.domain.dispatchers.DispatchersProvider
import com.bokorzslt.domain.features.details.models.MovieDetails
import com.bokorzslt.domain.features.details.usecase.GetMovieDetailsUseCase
import com.bokorzslt.ui.generic.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class DetailsViewModel(
    movieId: Long,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    dispatchersProvider: DispatchersProvider
) : BaseViewModel(dispatchersProvider) {

    sealed class DetailsUiState {
        data object Loading : DetailsUiState()
        data class Success(val movie: MovieDetails) : DetailsUiState()
        data class Error(val throwable: Throwable) : DetailsUiState()
    }

    private val mutableState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val state: StateFlow<DetailsUiState> = mutableState

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        mutableState.value = DetailsUiState.Error(throwable)
    }

    init {
        Timber.d("Load details for movie: $movieId")
        loadMovieDetails(movieId)
    }

    private fun loadMovieDetails(movieId: Long) =
        launchOnIO(errorHandler) {
            mutableState.value =
                DetailsUiState.Success(getMovieDetailsUseCase(movieId))
        }
}