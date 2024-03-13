package com.bokorzslt.domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {

    fun getMain(): CoroutineDispatcher

    fun getMainImmediate(): CoroutineDispatcher

    fun getIO(): CoroutineDispatcher

    fun getDefault(): CoroutineDispatcher
}
