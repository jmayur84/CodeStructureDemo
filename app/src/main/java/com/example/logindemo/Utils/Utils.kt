package com.example.logindemo.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import retrofit2.Response


class Utils {

    companion object {


        const val TYPE_WIFI = 1
        const val TYPE_MOBILE = 2
        const val TYPE_NOT_CONNECTED = 0
        const val NETWORK_STATUS_NOT_CONNECTED = 0
        const val NETWORK_STATUS_WIFI = 1
        const val NETWORK_STATUS_MOBILE = 2
        const val STATUS_SUCCESS = 200
        const val STATUS_NO_NETWORK = 600


        fun isEmailValid(editText: EditText): Boolean {

            return (!TextUtils.isEmpty(editText.text) && Patterns.EMAIL_ADDRESS.matcher(editText.text).matches())

        }

        fun setError(editText: EditText, msg: String?) {
            editText.error = msg
        }


        fun isInternetAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnected) {
                    return true
                }
            }
            return false
        }

        fun filterResponse(t: Response<*>?, context: Context): Boolean {
            return if (t != null) {
                when {
                    t.code() == STATUS_SUCCESS -> true
                    t.code() == STATUS_NO_NETWORK -> {
                        showNoInternetDialog(context)
                        false
                    }
                    else -> {
                        showToast(t.message(), context)
                        false
                    }
                }
            } else {
                showToast("Something went wrong.Please try later", context)
                false
            }


        }


        fun showToast(msg: String, context: Context) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        private fun showNoInternetDialog(mcontext: Context) {
            val alertDialog = AlertDialog.Builder(mcontext)

            alertDialog.setTitle("Info")
            alertDialog.setCancelable(false)
            alertDialog.setMessage("Internet not available, Please check your internet connectivity and try again")
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert)
            alertDialog.setPositiveButton("OK") { dialog, _ -> dialog!!.dismiss() }

            alertDialog.show()
        }
    }


}