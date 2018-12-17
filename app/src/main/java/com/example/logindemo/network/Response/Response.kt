package com.example.logindemo.network.Response.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Response {

    @field:SerializedName("status")
    @Expose
    var status: Boolean? = null

    @field:SerializedName("message")
    @Expose
    var message: String? = null

}