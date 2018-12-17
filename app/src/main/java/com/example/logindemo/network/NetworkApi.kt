package com.example.logindemo.network

import com.example.logindemo.network.Request.LoginRequest
import com.example.logindemo.network.Response.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface NetworkApi {

    @POST(Urls.login)
    fun getLogin(@Body request: LoginRequest): Observable<Response<LoginResponse>>

    @POST("501?sleep=100020")
    fun getError(@Body request: LoginRequest): Observable<Response<LoginResponse>>
}