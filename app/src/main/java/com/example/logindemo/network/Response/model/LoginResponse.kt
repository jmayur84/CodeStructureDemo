package com.example.logindemo.network.Response.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (

	@field:SerializedName("token")
	val token: String? = null
): Response()