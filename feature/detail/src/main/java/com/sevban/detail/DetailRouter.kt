package com.sevban.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.uber.rib.core.BasicComposeRouter
import com.uber.rib.core.ComposePresenter

class DetailRouter(
    presenter: ComposePresenter,
    interactor: DetailInteractor,
    slot: MutableState<(@Composable () -> Unit)>,
) : BasicComposeRouter<DetailInteractor>(presenter, interactor, slot)
