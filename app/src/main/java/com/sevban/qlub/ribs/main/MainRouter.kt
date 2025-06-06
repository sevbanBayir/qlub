package com.sevban.qlub.ribs.main

import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import com.sevban.common.streams.ItemsStream
import com.sevban.detail.DetailRouter
import com.sevban.detail.DetailScope
import com.sevban.detail.DetailScopeDependencies
import com.sevban.list.ListInteractor
import com.sevban.list.ListRouter
import com.sevban.list.ListScope
import com.sevban.list.ListScopeDependencies
import com.sevban.model.Product
import com.uber.rib.core.BasicViewRouter
import com.uber.rib.core.ComposePresenter
import motif.ScopeFactory

class MainRouter(
    view: ComposeView,
    interactor: MainInteractor,
    private val parentView: ViewGroup,
    private val scope: MainScope,
    private val childContent: ChildContent,
    private val presenter: ComposePresenter,
) : BasicViewRouter<ComposeView, MainInteractor>(view, interactor) {

    private var listRouter: ListRouter? = null
    var detailRouter: DetailRouter? = null
        private set

    override fun willAttach() {
        super.willAttach()
        parentView.addView(view)

        view.setContent { 
            presenter.composable.invoke()
        }
    }

    override fun willDetach() {
        parentView.removeView(view)
        super.willDetach()
    }

    internal fun attachList() {
        val listScopeDependencies = object : ListScopeDependencies {
            override fun slot(): MutableState<@Composable () -> Unit> {
                return childContent.fullScreenSlot
            }

            override fun onItemClickedListener(): ListInteractor.Listener {
                return interactor
            }

            override fun itemStream(): ItemsStream {
                return interactor.itemsStream
            }
        }
        listRouter = ScopeFactory.create(ListScope::class.java, listScopeDependencies).router()
        listRouter?.let { attachChild(it) }
    }

    internal fun detachList() {
        listRouter?.let { detachChild(it) }
        listRouter = null
    }

    internal fun attachDetail(product: Product) {

        val detailScopeDependencies = object : DetailScopeDependencies {
            override fun slot(): MutableState<@Composable () -> Unit> {
                return childContent.fullScreenSlot
            }

            override fun product(): Product {
                return product
            }
        }
        detailRouter =
            ScopeFactory.create(DetailScope::class.java, detailScopeDependencies).router()
        detailRouter?.let { attachChild(it) }
    }

    internal fun detachDetail() {
        detailRouter?.let { detachChild(it) }
        detailRouter = null
    }

    class ChildContent {
        internal var fullScreenSlot: MutableState<(@Composable () -> Unit)> = mutableStateOf({})
    }
}


