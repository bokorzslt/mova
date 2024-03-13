package com.bokorzslt.domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultDispatchersProvider : DispatchersProvider {

    override fun getMain(): CoroutineDispatcher = Dispatchers.Main

    override fun getMainImmediate(): CoroutineDispatcher = Dispatchers.Main.immediate

    override fun getIO(): CoroutineDispatcher = Dispatchers.IO

    override fun getDefault(): CoroutineDispatcher = Dispatchers.Default

}
