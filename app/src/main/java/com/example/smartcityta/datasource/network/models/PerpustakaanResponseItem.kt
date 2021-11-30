package com.example.smartcityta.datasource.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PerpustakaanResponseItem(

	@field:SerializedName("fitur")
	val fitur: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("published_at")
	val publishedAt: String,

	@field:SerializedName("total_pengunjung")
	val totalPengunjung: Int,

	@field:SerializedName("deskripsi")
	val deskripsi:String?,

	@field:SerializedName("long")
	val long:Double,

	@field:SerializedName("lat")
	val lat:Double,

	@field:SerializedName("gambar")
	val gambar: List<GambarItem>,

	@field:SerializedName("alamat")
	val alamat: String
):Parcelable