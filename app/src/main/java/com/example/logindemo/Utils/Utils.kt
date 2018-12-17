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


        fun isEmailValid(editText: EditText): Boolean {

            return (!TextUtils.isEmpty(editText.text) && Patterns.EMAIL_ADDRESS.matcher(editText.text).matches())

        }

        fun setError(editText: EditText, msg: String?) {
            editText.error = msg
        }


        fun isInternetAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
            return false
        }

        fun filterResponse(t: Response<*>?, context: Context): Boolean {
            return if (t != null) {
                when {
                    t.code() == 200 -> true
                    t.code() == 606 -> {
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

        fun showNoInternetDialog(mcontext: Context) {
            val alertDialog = AlertDialog.Builder(mcontext)

            alertDialog.setTitle("Info")
            alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again")
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert)
            alertDialog.setPositiveButton(
                "OK"
            ) { dialog, which -> dialog!!.dismiss() }

            alertDialog.show()
        }
    }


}