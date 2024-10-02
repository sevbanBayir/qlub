package com.sevban.qlub

import android.content.Context
import android.view.ViewGroup
import com.sevban.data.di.DataScope
import com.sevban.data.repository.ProductsOfflineFirstRepository
import com.sevban.qlub.ribs.main.MainScope
import com.sevban.ribapi.root.RootView
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.RibActivity
import motif.Expose

@motif.Scope
interface RootScope {
    fun router(): RootRouter

    fun mainScope(
        parentViewGroup: ViewGroup,
        productsOfflineFirstRepository: ProductsOfflineFirstRepository
    ): MainScope

    fun databaseScope(): DataScope

    @motif.Objects
    abstract class Objects {
        abstract fun router(): RootRouter

        abstract fun interactor(): RootInteractor

        abstract fun presenter(): EmptyPresenter

        fun view(parentViewGroup: ViewGroup): RootView {
            return RootView(parentViewGroup.context)
        }

        @Expose
        fun context(activity: RibActivity): Context {
            return activity.applicationContext
        }

    }
}
