package com.example.logindemo.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

abstract class NetworkCheckInterceptor : Interceptor {

    abstract fun isInternetAvailable(): Boolean
    abstract fun onInternetUnAvailable()


    override fun intercept(chain: Interceptor.Chain): Response? {
        val original = chain.request()

        return if (!isInternetAvailable()) {
            onInternetUnAvailable()
            Response(606,"Please check internet connection.")
        } else {
            chain.proceed(original)
        }


    }
}