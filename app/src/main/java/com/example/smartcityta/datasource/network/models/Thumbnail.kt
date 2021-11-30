package com.example.smartcityta.datasource.network.models

import com.google.gson.annotations.SerializedName

data class Thumbnail(

	@field:SerializedName("ext")
	val ext: String,

	@field:SerializedName("path")
	val path: Any,

	@field:SerializedName("size")
	val size: Double,

	@field:SerializedName("mime")
	val mime: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("width")
	val width: Int,

	@field:SerializedName("hash")
	val hash: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("height")
	val height: Int
)