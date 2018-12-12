package com.example.logindemo.view.Login.activity

import android.os.Bundle
import com.example.logindemo.R
import com.example.logindemo.ui.dialogs.LoadingDialog

class LoginActivity : BaseActivity(), LoginContract.View {
    override fun initializePresenter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initFragment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun getLoadingDialog(): LoadingDialog {
        return loading
    }

}
