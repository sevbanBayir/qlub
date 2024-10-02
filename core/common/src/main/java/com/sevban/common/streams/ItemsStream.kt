package com.sevban.common.streams

import com.sevban.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ItemsStream {
    private val _itemsFlow = MutableStateFlow(emptyList<Product>())
    private val itemsFlow = _itemsFlow.asStateFlow()

    fun observe() = itemsFlow
    fun update(items: List<Product>) {
        _itemsFlow.update { items }
    }
}