package com.bokorzslt.ui.features.home

import com.bokorzslt.domain.dispatchers.DispatchersProvider
import com.bokorzslt.domain.features.home.models.HomePageModule
import com.bokorzslt.domain.features.home.usecase.GetHomePageStructureUseCase
import com.bokorzslt.ui.generic.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val getHomePageStructureUseCase: GetHomePageStructureUseCase,
    dispatchersProvider: DispatchersProvider
) : BaseViewModel(dispatchersProvider) {

    sealed class HomeUiState {
        data object Loading : HomeUiState()
        data class Success(val modules: List<HomePageModule>) : HomeUiState()
        data class Error(val throwable: Throwable) : HomeUiState()
    }

    private val mutableState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val state: StateFlow<HomeUiState> = mutableState

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        mutableState.value = HomeUiState.Error(exception)
    }

    init {
        loadHomePage()
    }

    private fun loadHomePage() {
        launchOnIO(errorHandler) {
            mutableState.value = HomeUiState.Success(getHomePageStructureUseCase())
        }
    }
}