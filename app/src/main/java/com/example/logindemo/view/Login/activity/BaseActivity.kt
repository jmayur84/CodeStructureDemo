package com.example.logindemo.view.Login.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.logindemo.customclass.LoadingDialog

open class BaseActivity : AppCompatActivity() {

    lateinit var loading: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }




}