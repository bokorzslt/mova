package com.bokorzslt.ui.features.details.tabs.comments

import com.bokorzslt.domain.dispatchers.DispatchersProvider
import com.bokorzslt.domain.features.details.models.Review
import com.bokorzslt.domain.features.details.usecase.GetMovieReviewsUseCase
import com.bokorzslt.ui.generic.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class CommentsViewModel(
    movieId: Long,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    dispatchersProvider: DispatchersProvider
) : BaseViewModel(dispatchersProvider) {

    sealed class CommentsUiState {
        data object Loading : CommentsUiState()
        data class Success(val reviews: List<Review>) : CommentsUiState()
        data class Error(val throwable: Throwable) : CommentsUiState()
    }

    private val mutableState = MutableStateFlow<CommentsUiState>(CommentsUiState.Loading)
    val state = mutableState.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable, "Error loading reviews for movie: $movieId")
        mutableState.value = CommentsUiState.Error(throwable)
    }

    init {
        loadMovieReviews(movieId)
    }

    private fun loadMovieReviews(movieId: Long) {
        launchOnIO(errorHandler) {
            mutableState.value = CommentsUiState.Success(getMovieReviewsUseCase(movieId))
        }
    }
}