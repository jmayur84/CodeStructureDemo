package com.example.logindemo.implementors

import android.content.Context
import com.example.logindemo.Utils.Utils
import com.example.logindemo.interactors.LoginInteractors
import com.example.logindemo.network.NetworkService
import com.example.logindemo.network.Request.LoginRequest
import com.example.logindemo.network.Response.model.IAPICallBack
import com.example.logindemo.network.Response.model.LoginResponse
import retrofit2.Response
import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginImplementor(networkService: NetworkService) : LoginInteractors() {

    private var subscription: Subscription? = null

    private lateinit var context: Context


    private val mNetworkService: NetworkService = networkService

    override fun getLogin(context: Context, request: LoginRequest, iCallBack: IAPICallBack<LoginResponse>) {

        this.context = context
        val observable: (Observable<Response<LoginResponse>>) = (mNetworkService.getPreparedObservable(
            mNetworkService.getApi().getLogin(request),
            LoginResponse::class.java,
            false,
            false
        ) as Observable<Response<LoginResponse>>)



        subscription = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Response<LoginResponse>>() {
                override fun onNext(t: Response<LoginResponse>?) {
                    if (Utils.filterResponse(t, context)) {
                        iCallBack.onSuccess(t!!.body()!!)
                    } else {
                        iCallBack.stopProgress()
                    }

                }

                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                    iCallBack.onFailure(e.toString())
                }
            })
    }


    override fun rxUnSubscribe() {
        if (subscription != null && !subscription!!.isUnsubscribed) {
            subscription!!.unsubscribe()
        }
    }
}