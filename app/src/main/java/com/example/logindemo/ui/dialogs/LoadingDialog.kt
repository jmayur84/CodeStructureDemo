package com.example.logindemo.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.example.logindemo.R

class LoadingDialog private constructor() {

    private lateinit var dialog: Dialog


    companion object {

        private val mInstance: LoadingDialog = LoadingDialog()

        @Synchronized
        fun getInstance(): LoadingDialog {
            return mInstance
        }
    }


    fun showDialog(context: Context) {
        dialog = Dialog(context)
        dialog.window.setBackgroundDrawable(ColorDrawable(0x00000000))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.loading_dialog)
        dialog.setCancelable(false)

        if (!dialog.isShowing) {
            dialog.show()
        }
    }


    fun showDialogWithTitle(context: Context, title: String) {

        dialog = Dialog(context)
        dialog.window.setBackgroundDrawable(ColorDrawable(0x00000000))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.loading_dialog)
        dialog.setCancelable(false)
        dialog.setTitle(title)

        dialog.show()

    }

    fun dismissDialog() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }


}