package com.emirerkut.common.helper

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val ioDispatcher: CoroutineDispatcher
    val defaultDispatcher: CoroutineDispatcher
    val mainDispatcher: CoroutineDispatcher
    val unconfinedDispatcher: CoroutineDispatcher
}