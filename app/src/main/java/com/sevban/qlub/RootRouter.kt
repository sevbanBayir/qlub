package com.sevban.qlub

import com.sevban.qlub.ribs.main.MainRouter
import com.sevban.ribapi.root.RootView
import com.uber.rib.core.BasicViewRouter

class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    private val scope: RootScope,
) : BasicViewRouter<RootView, RootInteractor>(view, interactor) {

    var mainRouter: MainRouter? = null
        private set

    override fun willAttach() {
        attachMain()
    }

    override fun willDetach() {
        detachMain()
    }

    private fun attachMain() {
        if (mainRouter == null) {
            mainRouter = scope.mainScope(view, scope.dataScope().productRepository()).router()
                .also { attachChild(it) }
        }
    }

    fun detachDetail() {
        mainRouter?.detachDetail()
        mainRouter?.attachList()
    }

    private fun detachMain() {
        mainRouter?.let { detachChild(it) }
    }
}
