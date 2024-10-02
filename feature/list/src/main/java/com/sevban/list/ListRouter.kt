package com.sevban.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.uber.rib.core.BasicComposeRouter
import com.uber.rib.core.ComposePresenter

class ListRouter(
    presenter: ComposePresenter,
    interactor: ListInteractor,
    slot: MutableState<(@Composable () -> Unit)>,
) : BasicComposeRouter<ListInteractor>(presenter, interactor, slot)
