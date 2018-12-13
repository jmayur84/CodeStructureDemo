package com.example.logindemo.interactors

import com.example.logindemo.network.Request.Request
import com.example.logindemo.network.Response.model.IAPICallBack
import com.example.logindemo.network.Response.model.LoginResponse

abstract class LoginInteractors {

    abstract fun getLogin(request: Request, iCallBack: IAPICallBack<LoginResponse>)
    abstract fun rxUnSubscribe()
}