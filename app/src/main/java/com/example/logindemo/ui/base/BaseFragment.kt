package com.example.logindemo.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.logindemo.ui.dialogs.LoadingDialog

abstract class BaseFragment : Fragment() {
    lateinit var loading: LoadingDialog
    lateinit var basePresenter: BasePresenter<*>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preLoadData()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loading = LoadingDialog.getInstance()
        return inflater.inflate(getContentView(), container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initData(savedInstanceState)
        initializePresenter()
        setListeners()

    }

    abstract fun setListeners()

    abstract fun initializePresenter()

    abstract fun initData(savedInstanceState: Bundle?)

    abstract fun initViews(view: View)

    /**
     * Method to show progress Dialog without title
     */
    fun showProgress() {
        loading.showDialog(context!!)
    }

    /**
     * Method to show progress Dialog with title
     */
    fun showProgress(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            loading.showDialogWithTitle(context!!, msg)
        }
    }

    /**
     * Method to stop progress Dialog
     */
    fun stopProgress() {
        loading.dismissDialog()
    }

    abstract fun getContentView(): Int

    open fun preLoadData() {}


    override fun onStart() {
        super.onStart()
        basePresenter.start()

    }

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        basePresenter.finalizeView()
    }
}