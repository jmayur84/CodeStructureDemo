package com.example.logindemo.view.Login.activity

import com.example.logindemo.R
import com.example.logindemo.presenter.LoginPresenter
import com.example.logindemo.view.Login.fragments.LoginFragment

class LoginActivity : BaseActivity() {


    override fun initFragment() {
        loadFragment(LoginFragment.newInstance(), true,false)
    }


    override fun initializePresenter() {
    }

    override fun setListeners() {
    }

    override fun initData() {
    }

    override fun initView() {
    }


    override fun getContentView(): Int {
        return R.layout.activity_main
    }

}
