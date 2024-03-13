package com.bokorzslt.ui.generic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bokorzslt.domain.dispatchers.DispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
    dispatchers: DispatchersProvider
) : ViewModel() {

    private val io = dispatchers.getIO()

    private val main = dispatchers.getMain()

    protected fun launchOnIO(
        errorHandler: CoroutineExceptionHandler,
        block: suspend CoroutineScope.() -> Unit
    ): Job = viewModelScope.launch(io + errorHandler, block = block)

    protected fun launchOnMain(
        errorHandler: CoroutineExceptionHandler,
        block: suspend CoroutineScope.() -> Unit
    ): Job = viewModelScope.launch(main + errorHandler, block = block)

    protected suspend fun <T> withContextIO(
        errorHandler: CoroutineExceptionHandler,
        block: suspend CoroutineScope.() -> T
    ): T = withContext(io + errorHandler, block)

    protected suspend fun <T> withContextMain(
        errorHandler: CoroutineExceptionHandler,
        block: suspend CoroutineScope.() -> T
    ): T = withContext(main + errorHandler, block)
}
