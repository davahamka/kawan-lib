package com.example.smartcityta.datasource.network.models

import com.google.gson.annotations.SerializedName

data class PerpustakaanResponse(

	@field:SerializedName("PerpustakaanResponse")
	val perpustakaanResponse: List<PerpustakaanResponseItem>
)