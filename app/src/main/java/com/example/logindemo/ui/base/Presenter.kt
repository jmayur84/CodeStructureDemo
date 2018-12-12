package com.example.logindemo.ui.base

import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean

abstract class Presenter<T : Presenter.View> {

    private lateinit var view: WeakReference<T>

    private lateinit var isViewAlive: AtomicBoolean

    fun getView(): T? {
        return view.get()
    }

    fun setView(view: T) {
        this.view = WeakReference(view)
    }


    fun initialize() {}


    fun start() {
        isViewAlive.set(true)
    }

    fun finalizeView() {
        isViewAlive.set(false)
    }

    abstract fun rxUnSubscribe()

    interface View
}