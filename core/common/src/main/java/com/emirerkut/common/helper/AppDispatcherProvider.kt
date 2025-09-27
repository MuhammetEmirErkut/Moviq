package com.emirerkut.common.helper

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AppDispatcherProvider @Inject constructor() : DispatcherProvider {
    override val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    override val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
    override val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
    override val unconfinedDispatcher: CoroutineDispatcher = Dispatchers.Unconfined
}