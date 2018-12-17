package com.example.logindemo.view.Login.activity

import com.example.logindemo.ui.base.BasePresenter

interface ILoginView : BasePresenter.View {

    fun showError(msg: String)

    fun showProgress()

    fun showProgress(msg:String)

    fun stopProgress()

}