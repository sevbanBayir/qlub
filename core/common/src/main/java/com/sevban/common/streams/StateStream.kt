package com.sevban.common.streams

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StateStream<T : Any>(default: T) {
    private val _stateFlow = MutableStateFlow(default)
    private val stateFlow = _stateFlow.asStateFlow()

    fun dispatch(viewModel: T) = _stateFlow.update { viewModel }

    fun observe() = stateFlow

    fun current() = stateFlow.value
}
