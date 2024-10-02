package com.sevban.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import com.sevban.common.streams.StateStream
import com.sevban.model.Product
import com.uber.rib.core.ComposePresenter
import motif.Creatable

interface DetailScopeDependencies {
    fun slot(): MutableState<(@Composable () -> Unit)>
    fun product(): Product
}

@motif.Scope
interface DetailScope : Creatable<DetailScopeDependencies> {
    fun router(): DetailRouter

    @motif.Objects
    abstract class Objects {
        abstract fun router(): DetailRouter

        abstract fun interactor(): DetailInteractor

        fun presenter(
            stateStream: StateStream<Product>,
        ): ComposePresenter {
            return object : ComposePresenter() {
                override val composable =
                    @Composable {
                        DetailView(
                            stateStream.observe().collectAsState(initial = stateStream.current()),
                        )
                    }
            }
        }

        fun stateStream(product: Product) = StateStream(product)
    }
}
