package com.sevban.common.streams

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class EventStream<T> {
    private val _sharedFlow = MutableSharedFlow<T>(extraBufferCapacity = 1)
    private val sharedFlow = _sharedFlow.asSharedFlow()

    fun notify(event: T) = _sharedFlow.tryEmit(event)

    fun observe() = sharedFlow
}
