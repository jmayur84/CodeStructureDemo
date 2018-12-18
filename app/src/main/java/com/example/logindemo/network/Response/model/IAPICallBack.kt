package com.example.logindemo.network.Response.model

import android.content.Context
import com.example.logindemo.Utils.Utils
import com.example.logindemo.view.Login.activity.BaseActivity

abstract class IAPICallBack<T : Response>(val context: Context) {

    abstract fun onSuccess(response: T)

    fun onFailure(str: String) {
        Utils.showToast(str, context)
    }

    fun stopProgress() {
        (context as BaseActivity).stopProgress()
    }
}