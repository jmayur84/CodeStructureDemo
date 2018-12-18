package com.example.logindemo.network.interceptors

import com.example.logindemo.Utils.Utils
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody


abstract class NetworkCheckInterceptor : Interceptor {

    abstract fun isInternetAvailable(): Boolean


    override fun intercept(chain: Interceptor.Chain): Response? {
        val original = chain.request()

        return if (!isInternetAvailable()) {
            return createCustomResponse(chain)
        } else {
            chain.proceed(original)
        }


    }

    private fun createCustomResponse(chain: Interceptor.Chain): Response {
        return Response.Builder().request(chain.request()).protocol(Protocol.HTTP_2).body(ResponseBody.create(null, ""))
            .code(Utils.STATUS_NO_NETWORK).message("Please check internet connection.").build()

    }
}