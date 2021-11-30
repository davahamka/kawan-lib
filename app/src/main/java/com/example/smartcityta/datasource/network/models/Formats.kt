package com.example.smartcityta.datasource.network.models

import com.google.gson.annotations.SerializedName

data class Formats(

	@field:SerializedName("small")
	val small: Small,

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail
)