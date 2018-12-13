package com.example.logindemo.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.logindemo.ui.dialogs.LoadingDialog

abstract class BaseFragment : Fragment() {
    lateinit var loading: LoadingDialog
    var basePresenter: BasePresenter<BasePresenter.View>? = null


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


    abstract fun getContentView(): Int

    fun preLoadData() {}


    override fun onStart() {
        super.onStart()
        if (basePresenter != null) {
            basePresenter!!.start()
        }

    }

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        if (basePresenter != null) {
            basePresenter!!.finalizeView()
        }
    }
}