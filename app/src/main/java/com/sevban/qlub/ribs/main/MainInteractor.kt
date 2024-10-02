package com.sevban.qlub.ribs.main

import com.sevban.common.streams.ItemsStream
import com.sevban.data.repository.ProductsOfflineFirstRepository
import com.sevban.list.ListInteractor
import com.uber.rib.core.BasicInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.ComposePresenter
import com.uber.rib.core.coroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainInteractor(
    presenter: ComposePresenter,
    private val productsOfflineFirstRepository: ProductsOfflineFirstRepository,
    val itemsStream: ItemsStream,
    private val childContent: MainRouter.ChildContent,
) : BasicInteractor<ComposePresenter, MainRouter>(presenter), ListInteractor.Listener {

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        coroutineScope.launch {
            val products = productsOfflineFirstRepository.getProducts()
            itemsStream.update(products)
        }

        router.view.setContent { MainView(childContent = childContent) }
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
}
