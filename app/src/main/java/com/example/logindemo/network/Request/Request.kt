package com.example.logindemo.network.Request

import com.google.gson.annotations.SerializedName

open class Request(

    @field:SerializedName("api_key")
     var apiKey: String? = null
)
