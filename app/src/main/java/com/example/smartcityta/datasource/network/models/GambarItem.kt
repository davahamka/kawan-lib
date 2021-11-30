package com.example.smartcityta.datasource.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GambarItem(

	@field:SerializedName("ext")
	val ext: String,


	@field:SerializedName("mime")
	val mime: String,

	@field:SerializedName("caption")
	val caption: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("size")
	val size: Double,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("provider")
	val provider: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("width")
	val width: Int,


	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("alternativeText")
	val alternativeText: String,

	@field:SerializedName("hash")
	val hash: String,

	@field:SerializedName("height")
	val height: Int
):Parcelable