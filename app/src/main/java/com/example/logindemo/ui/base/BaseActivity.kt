package com.example.logindemo.view.Login.activity

import android.os.Bundle
import android.support.design.internal.NavigationMenuPresenter
import android.support.v7.app.AppCompatActivity
import com.example.logindemo.R
import com.example.logindemo.ui.base.Presenter
import com.example.logindemo.ui.dialogs.LoadingDialog

open abstract class BaseActivity : AppCompatActivity() {

    lateinit var loading: LoadingDialog
    lateinit var loading1: LoadingDialog
    lateinit var loading2: LoadingDialog
    private val presenter: Presenter<Presenter.View>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getContentView())

//        initFragment()

        loading = LoadingDialog.getInstance()
        loading1 = LoadingDialog.getInstance()
        loading2 = LoadingDialog.getInstance()

//        initView()

//        initData()

//        setListeners()

//        initializePresenter()
    }

    abstract fun initializePresenter()

    abstract fun setListeners()

    abstract fun initData()

    abstract fun initView()

    abstract fun initFragment()

    abstract fun getContentView():Int


    override fun onStart() {
        super.onStart()
        presenter?.start()
    }


    override fun onStop() {
        super.onStop()
            presenter?.finalizeView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
    }



}