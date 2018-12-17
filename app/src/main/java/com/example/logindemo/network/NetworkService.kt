package com.example.logindemo.network

import android.content.Context
import android.support.v4.util.LruCache
import com.example.logindemo.BuildConfig
import com.example.logindemo.Utils.Utils
import com.example.logindemo.network.interceptors.NetworkCheckInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers.newThread
import java.util.concurrent.TimeUnit

class NetworkService(context: Context, val mConnectionListener: InternetConnectionListener?) {

    var mcontext = context

    lateinit var networkApi: NetworkApi
    lateinit var okHttpClient: OkHttpClient
    lateinit var apiObservables: LruCache<Class<*>, Observable<*>>


    init {
        networkService(Urls.BASE_URL)
    }


    fun networkService(baseUrl: String) {
        okHttpClient = buildOkHttpClient()

        val gson = GsonBuilder().setLenient().create()


        apiObservables = LruCache(10)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        networkApi = retrofit.create(NetworkApi::class.java)
    }


    fun getApi(): NetworkApi {
        return networkApi
    }


    fun clearCache() {
        apiObservables.evictAll()
    }


    private fun buildOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()


        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        okHttpBuilder.addInterceptor(httpLoggingInterceptor)
            .addInterceptor(object : NetworkCheckInterceptor() {
                override fun isInternetAvailable(): Boolean {
                    return Utils.isInternetAvailable(mcontext)
                }

                override fun onInternetUnAvailable() {

                    mConnectionListener!!.onInternetUnavailable()

                }
            })
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)


        return okHttpBuilder.build()
    }


    fun getPreparedObservable(
        unPreparedObservable: Observable<*>,
        clazz: Class<*>,
        cacheObservable: Boolean,
        useCache: Boolean
    ): Observable<*> {

        var preparedObservable: Observable<*>? = null

        if (useCache) {
            preparedObservable = apiObservables.get(clazz)
        }

        if (preparedObservable != null) {
            return preparedObservable
        }


        preparedObservable =
                unPreparedObservable.subscribeOn(newThread()).observeOn(AndroidSchedulers.mainThread())

        if (cacheObservable) {
            preparedObservable = preparedObservable.cache()
            apiObservables.put(clazz, preparedObservable)
        }

        return preparedObservable
    }
}