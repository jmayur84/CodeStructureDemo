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

class NetworkService(context: Context) {

    var mcontext = context

    lateinit var networkApi: NetworkApi
    lateinit var okHttpClient: OkHttpClient
    lateinit var apiObservables: LruCache<Class<*>, Observable<*>>


    init {
        networkService(Urls.BASE_URL)
    }


    fun networkService(baseUrl: String) {
        okHttpClient = buildOkHttpClient()

        val gson = GsonBuilder().create()


        apiObservables = LruCache(10)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        networkApi = retrofit.create(NetworkApi::class.java)
    }


    /**
     * Method to return the API interface.
     *
     * @return
     */
    fun getApi(): NetworkApi {
        return networkApi
    }

    /**
     * Method to clear the entire cache of observables
     */
    fun clearCache() {
        apiObservables.evictAll()
    }

    /**
     * Method to build and return an OkHttpClient so we can set/get
     * headers quickly and efficiently.
     *
     * @return
     */
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
            })
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)


        return okHttpBuilder.build()
    }


    /**
     * Method to either return a cached observable or prepare a new one.
     *
     * @param unPreparedObservable
     * @param clazz
     * @param cacheObservable
     * @param useCache
     * @return Observable ready to be subscribed to
     * We should use this method for network calls when we support application for both Portrait and Landscape mode and there is
     * a chance that user can rotate device while network call is in progress, in that case pass "cacheObservable" and "useCache"
     * these both booleans as "true" which will avoid leakage in request and will return response even if device is rotated in
     * between network call is in progress.
     */
    fun getPreparedObservable(unPreparedObservable: Observable<*>, clazz: Class<*>, cacheObservable: Boolean, useCache: Boolean): Observable<*> {

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