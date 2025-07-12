package com.sevban.qlub.ribs.main

import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.sevban.common.streams.ItemsStream
import com.sevban.list.ListInteractor
import com.uber.rib.core.EmptyPresenter
import motif.Expose
import motif.Scope

@Scope
interface MainScope {
    fun router(): MainRouter

    @motif.Objects
    abstract class Objects {
        abstract fun router(): MainRouter

        abstract fun interactor(): MainInteractor

        abstract fun presenter(): EmptyPresenter

        abstract fun childContent(): MainRouter.ChildContent

        fun view(parentViewGroup: ViewGroup): ComposeView {
            return ComposeView(parentViewGroup.context)
        }

        @Expose
        abstract fun itemsStream(): ItemsStream

        @Expose
        abstract fun onItemClickedListener(interactor: MainInteractor): ListInteractor.Listener
    }
}
