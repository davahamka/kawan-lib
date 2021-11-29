package com.example.smartcityta.datasource.network.models

import com.google.gson.annotations.SerializedName

data class AuthResponse(

	@field:SerializedName("AuthResponse")
	val authResponse: List<AuthResponseItem>
)