package com.example.logindemo.app

import android.app.Application
import com.example.logindemo.network.NetworkService
import com.example.logindemo.network.InternetConnectionListener


class LoginDemo : Application() {
    var mConnectionListener: InternetConnectionListener? = null
    lateinit var networkService: NetworkService

    companion object {

        private var ourInstance: LoginDemo? = null


        @Synchronized
        fun getInstance(): LoginDemo {
            return ourInstance!!
        }

    }


    override fun onCreate() {
        super.onCreate()
        ourInstance = this
    }


    fun setInternetConnectionListener(listener: InternetConnectionListener) {
        mConnectionListener = listener
        networkService = NetworkService(this, mConnectionListener)
    }

    fun removeInternetConnectionListener() {
        mConnectionListener = null
    }
}