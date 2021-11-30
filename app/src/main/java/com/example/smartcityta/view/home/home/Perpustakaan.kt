package com.example.smartcityta.view.home.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Perpustakaan(
    var namaPerpustakaan: String,
    var alamatPerpustakaan: String,
    var totalPengunjung: String,
):Parcelable
