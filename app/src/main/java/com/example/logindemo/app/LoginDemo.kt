package com.example.logindemo.app

import android.app.Application
import com.example.logindemo.network.NetworkService


class LoginDemo : Application() {

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
        networkService = NetworkService(this)
    }

}