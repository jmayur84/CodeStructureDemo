package com.example.logindemo.view.Login.activity

import android.os.Bundle
import com.example.logindemo.R
import com.example.logindemo.customclass.LoadingDialog

class LoginActivity : BaseActivity(), LoginContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    override fun getLoadingDialog(): LoadingDialog {
        return loading
    }

}
