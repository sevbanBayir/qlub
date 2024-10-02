package com.sevban.qlub

import com.uber.rib.core.BasicInteractor
import com.uber.rib.core.EmptyPresenter

class RootInteractor(presenter: EmptyPresenter) :
    BasicInteractor<EmptyPresenter, RootRouter>(presenter) {

    override fun handleBackPress(): Boolean {
        val router = router
        val isDetailAttached = router.mainRouter?.detailRouter != null
        if (isDetailAttached) {
            router.detachDetail()
            return true
        }
        return false
    }
}
