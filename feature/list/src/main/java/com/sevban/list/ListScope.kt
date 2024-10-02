package com.sevban.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import com.sevban.common.streams.EventStream
import com.sevban.common.streams.ItemsStream
import com.sevban.common.streams.StateStream
import com.uber.rib.core.ComposePresenter
import motif.Creatable

interface ListScopeDependencies {
    fun slot(): MutableState<(@Composable () -> Unit)>
    fun onItemClickedListener(): ListInteractor.Listener
    fun itemStream(): ItemsStream
}

@motif.Scope
interface ListScope : Creatable<ListScopeDependencies> {
    fun router(): ListRouter

    @motif.Objects
    abstract class Objects {
        abstract fun router(): ListRouter

        abstract fun interactor(): ListInteractor

        fun presenter(
            stateStream: StateStream<List<ProductViewModel>>,
            eventStream: EventStream<ListEvent>,
        ): ComposePresenter {
            return object : ComposePresenter() {
                override val composable =
                    @Composable {
                        ListView(
                            stateStream.observe().collectAsState(initial = stateStream.current()),
                            eventStream,
                        )
                    }
            }
        }

        fun eventStream() = EventStream<ListEvent>()

        fun stateStream() = StateStream(emptyList<ProductViewModel>())
    }
}
