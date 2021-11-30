package com.example.smartcityta.datasource.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthResponseItem(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("alamat")
	val alamat: String
):Parcelable