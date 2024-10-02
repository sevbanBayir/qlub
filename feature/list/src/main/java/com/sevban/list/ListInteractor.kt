package com.sevban.list

import com.sevban.common.streams.EventStream
import com.sevban.common.streams.ItemsStream
import com.sevban.common.streams.StateStream
import com.uber.rib.core.BasicInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.ComposePresenter
import com.uber.rib.core.coroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ListInteractor(
    presenter: ComposePresenter,
    private val itemsStream: ItemsStream,
    private val eventStream: EventStream<ListEvent>,
    private val stateStream: StateStream<List<ProductViewModel>>,
    private val listener: Listener
) : BasicInteractor<ComposePresenter, ListRouter>(presenter) {
    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        eventStream
            .observe()
            .onEach {
                when (it) {
                    is ListEvent.OnItemClick -> {
                        listener.onItemClicked(it.id)
                    }
                }
            }
            .launchIn(coroutineScope)

        itemsStream
            .observe()
            .onEach { items ->
                val productViewModels = items.map {
                    ProductViewModel(
                        id = it.id,
                        title = it.title,
                        description = it.description,
                        price = it.price.toString(),
                        imageUrl = it.thumbnail,
                        rating = it.rating.toString(),
                        category = it.category,
                        reviewCount = it.reviews.size.toString()
                    )
                }
                stateStream.dispatch(
                    productViewModels
                )
            }.launchIn(coroutineScope)
    }

    interface Listener {
        fun onItemClicked(id: Int)
    }
}
