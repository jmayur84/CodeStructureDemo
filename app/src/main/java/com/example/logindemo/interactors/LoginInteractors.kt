package com.example.logindemo.interactors

import android.content.Context
import com.example.logindemo.network.Request.LoginRequest
import com.example.logindemo.network.Response.model.IAPICallBack
import com.example.logindemo.network.Response.model.LoginResponse

abstract class LoginInteractors {

    abstract fun getLogin(context: Context, request: LoginRequest, iCallBack: IAPICallBack<LoginResponse>)
    abstract fun rxUnSubscribe()

}