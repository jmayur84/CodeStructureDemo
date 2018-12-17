package com.example.logindemo.presenter

import android.content.Context
import com.example.logindemo.implementors.LoginImplementor
import com.example.logindemo.network.NetworkService
import com.example.logindemo.network.Request.LoginRequest
import com.example.logindemo.network.Response.model.IAPICallBack
import com.example.logindemo.network.Response.model.LoginResponse
import com.example.logindemo.ui.base.BasePresenter
import com.example.logindemo.view.Login.activity.ILoginView


class LoginPresenter(networkService: NetworkService) : BasePresenter<ILoginView>() {


    var loginImplementor: LoginImplementor = LoginImplementor(networkService)

    override fun rxUnSubscribe() {
        loginImplementor.rxUnSubscribe()
    }

    fun hitLoginApi(context: Context, req: LoginRequest) {
        loginImplementor.getLogin(context, req, object : IAPICallBack<LoginResponse> {
            override fun onSuccess(response: LoginResponse) {
                getView()!!.stopProgress()
                getView()!!.showError("Login Successful !!")
            }

            override fun onFailure(str: String) {
                getView()!!.stopProgress()
                getView()!!.showError(str)
            }
        })

    }


}