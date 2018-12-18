package com.example.logindemo.view.Login.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.example.logindemo.R
import com.example.logindemo.Utils.Utils
import com.example.logindemo.ui.base.BasePresenter
import com.example.logindemo.ui.dialogs.LoadingDialog

abstract class BaseActivity : AppCompatActivity() {

    lateinit var loading: LoadingDialog
    private var basePresenter: BasePresenter<BasePresenter.View>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())

        initFragment()

        loading = LoadingDialog.getInstance()

        initView()

        initData()

        setListeners()

        initializePresenter()
    }

    abstract fun initFragment()

    abstract fun initializePresenter()

    abstract fun setListeners()

    abstract fun initData()

    abstract fun initView()

    fun loadFragment(fragment: Fragment, addToBackStack: Boolean, animate: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()

        if (animate) {
            transaction.setCustomAnimations(
                R.anim.slide_from_right,
                R.anim.slide_to_left,
                R.anim.slide_from_left,
                R.anim.slide_to_right
            )
        }
        transaction.replace(R.id.activity_main, fragment, fragment.javaClass.simpleName)

        if (addToBackStack) {
            transaction.addToBackStack(fragment.javaClass.simpleName)
        }
        transaction.commit()
    }

    abstract fun getContentView(): Int


    fun showToast(msg: String) {
        Utils.showToast(msg, this)
    }

    /**
     * making activity presenter life cycle aware of activity
     * starting the
     */

    override fun onStart() {
        super.onStart()
        if (basePresenter != null) {
            basePresenter!!.start()
        }
    }


    /**
     * making activity presenter life cycle aware of activity
     */
    override fun onStop() {
        super.onStop()
        if (basePresenter != null) {
            basePresenter!!.finalizeView()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    /**
     * Method to stop progress Dialog
     */
    fun stopProgress() {
        loading.dismissDialog()
    }


    /**
     * Method to show progress Dialog without title
     */
    fun showProgress() {
        loading.showDialog(this)
    }


    /**
     * Method to show progress Dialog with title
     */
    fun showProgress(str: String) {
        if (!TextUtils.isEmpty(str)) {
            loading.showDialogWithTitle(this, str)
        }
    }


}