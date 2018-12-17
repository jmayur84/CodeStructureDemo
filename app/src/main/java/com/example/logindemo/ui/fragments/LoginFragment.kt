package com.example.logindemo.view.Login.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.logindemo.R
import com.example.logindemo.Utils.Utils
import com.example.logindemo.app.LoginDemo
import com.example.logindemo.network.Request.LoginRequest
import com.example.logindemo.presenter.LoginPresenter
import com.example.logindemo.ui.base.BaseFragment
import com.example.logindemo.view.Login.activity.ILoginView
import kotlinx.android.synthetic.main.layout_signin.*

class LoginFragment : BaseFragment(), ILoginView {

    lateinit var loginPresenter: LoginPresenter

    private fun isValid(): Boolean {


        return when {
            !Utils.isEmailValid(edittext1) -> {
                Utils.setError(edittext1, "please enter valid email")
                false
            }
            TextUtils.isEmpty(edittext2.text) -> {
                Utils.setError(edittext2, "please enter valid password")
                false
            }
            else -> true
        }
    }


    override fun showError(msg: String) {
        showToast(msg)
    }


    override fun setListeners() {
        button.setOnClickListener {
            if (isValid()) {
                val loginReq = LoginRequest(edittext1.text.toString(), edittext2.text.toString())
                showProgress()
                loginPresenter.hitLoginApi(context!!, loginReq)
            }
        }
    }


    override fun initializePresenter() {
        super.basePresenter = loginPresenter
        loginPresenter.setView(this)
    }


    override fun initData(savedInstanceState: Bundle?) {
        val networkService = ((activity!!.application) as LoginDemo).networkService
        loginPresenter = LoginPresenter(networkService)

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


    override fun onStop() {
        super.onStop()
        loginPresenter.rxUnSubscribe()
    }


}