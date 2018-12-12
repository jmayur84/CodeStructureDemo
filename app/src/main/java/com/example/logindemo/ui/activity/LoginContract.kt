package com.example.logindemo.view.Login.activity

import com.example.logindemo.ui.dialogs.LoadingDialog

interface LoginContract {

    interface presenter{

    }

    interface View{
        fun getLoadingDialog(): LoadingDialog
    }
}