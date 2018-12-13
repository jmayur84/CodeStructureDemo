package com.example.logindemo.view.Login.fragments

import android.os.Bundle
import android.view.View
import com.example.logindemo.R
import com.example.logindemo.ui.base.BaseFragment
import com.example.logindemo.view.Login.activity.ILoginView

class LoginFragment : BaseFragment(), ILoginView {


    override fun showError(msg: String) {
        showToast(msg)
    }

    override fun rxUnSubscribe() {

    }

    override fun setListeners() {
    }

    override fun initializePresenter() {
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initViews(view: View) {
    }

    override fun getContentView(): Int {
        return R.layout.frag_login
    }


    companion object {

        fun newInstance(): BaseFragment {
            return LoginFragment()
        }
    }


}