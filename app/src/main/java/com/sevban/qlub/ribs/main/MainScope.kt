package com.sevban.qlub.ribs.main

import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import com.sevban.common.streams.ItemsStream
import com.sevban.list.ListInteractor
import com.uber.rib.core.ComposePresenter
import motif.Expose
import motif.Scope

@Scope
interface MainScope {
    fun router(): MainRouter

    @motif.Objects
    abstract class Objects {
        abstract fun router(): MainRouter

        abstract fun interactor(): MainInteractor

        fun presenter(
            childContent: MainRouter.ChildContent,
        ): ComposePresenter {
            return object : ComposePresenter() {
                override val composable = @Composable { MainView(childContent) }
            }
        }

        fun view(parentViewGroup: ViewGroup): ComposeView {
            return ComposeView(parentViewGroup.context)
        }

        abstract fun childContent(): MainRouter.ChildContent

        @Expose
        abstract fun itemsStream(): ItemsStream

        @Expose
        abstract fun onItemClickedListener(interactor: MainInteractor): ListInteractor.Listener
    }
}
