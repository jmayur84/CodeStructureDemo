package com.example.logindemo.network.Response.model

interface IAPICallBack<T : Response> {
    fun onSuccess(response: T)
    fun onFailure(str: String)
    fun stopProgress()
}