package com.sevban.detail

import com.uber.rib.core.BasicInteractor
import com.uber.rib.core.ComposePresenter

class DetailInteractor(
    presenter: ComposePresenter,
) : BasicInteractor<ComposePresenter, DetailRouter>(presenter)