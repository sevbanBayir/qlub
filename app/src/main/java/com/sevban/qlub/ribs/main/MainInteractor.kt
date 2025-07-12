package com.sevban.qlub.ribs.main

import com.sevban.common.streams.ItemsStream
import com.sevban.data.repository.ProductsOfflineFirstRepository
import com.sevban.list.ListInteractor
import com.uber.rib.core.BasicInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch

class MainInteractor(
    presenter: EmptyPresenter,
    private val productsOfflineFirstRepository: ProductsOfflineFirstRepository,
    val itemsStream: ItemsStream,
) : BasicInteractor<EmptyPresenter, MainRouter>(presenter), ListInteractor.Listener {

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        coroutineScope.launch {
            productsOfflineFirstRepository.getAggregatedProducts()
                .retry {
                    delay(RETRY_INTERVAL)
                    true
                }
                .onEach {
                    itemsStream.update(it)
                }
                .launchIn(coroutineScope)
        }

        itemsStream
            .observe()
            .onEach {
                if (it.isNotEmpty())
                    router.attachList()
            }
            .launchIn(coroutineScope)
    }

    override fun onItemClicked(id: Int) {
        coroutineScope.launch {
            val product = productsOfflineFirstRepository.getProductById(id)
            router.detachList()
            router.attachDetail(product)
        }
    }

    companion object {
        const val RETRY_INTERVAL = 3000L
    }
}
