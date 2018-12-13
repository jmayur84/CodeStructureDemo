package com.example.logindemo.implementors

import com.example.logindemo.interactors.LoginInteractors
import com.example.logindemo.network.Request.Request
import com.example.logindemo.network.Response.model.IAPICallBack
import com.example.logindemo.network.Response.model.LoginResponse

class LoginImplementor : LoginInteractors {



    override fun getLogin(request: Request, iCallBack: IAPICallBack<LoginResponse>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rxUnSubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}