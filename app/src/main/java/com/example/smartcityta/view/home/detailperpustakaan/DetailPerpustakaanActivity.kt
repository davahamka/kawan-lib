package com.example.smartcityta.view.home.detailperpustakaan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.smartcityta.BuildConfig
import com.example.smartcityta.R
import com.example.smartcityta.databinding.ActivityDetailPerpustakaanBinding
import com.example.smartcityta.datasource.network.models.PerpustakaanResponseItem
import com.example.smartcityta.view.home.mapperpustakaan.PerpustakaanMapsActivity

class DetailPerpustakaanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPerpustakaanBinding

    companion object {
        const val EXTRA_PERPUSTAKAAN = "extra_perpustakaan"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPerpustakaanBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val perpus = intent.getParcelableExtra<PerpustakaanResponseItem>(EXTRA_PERPUSTAKAAN) as PerpustakaanResponseItem
        binding.textDetailNamaPerpustakaan.text = perpus.nama
        binding.textDetailAlamatPerpustakaan.text = perpus.alamat
        binding.textDetailDeskripsiPerpustakaan.text = perpus.deskripsi
        binding.textDetailFiturPerpustakaan.text = perpus.fitur
        binding.textDetailJumlahPerpustakaan.text = "${perpus.totalPengunjung} total pengunjung"
        Glide.with(view).load("${BuildConfig.BASE_URL_CMS}${perpus.gambar[0].url}").into(binding.imageViewPerpustakaan)

        binding.buttonLihatMap.setOnClickListener{
            val moveIntentWithData = Intent(this, PerpustakaanMapsActivity::class.java)
            moveIntentWithData.putExtra(PerpustakaanMapsActivity.EXTRA_PERPUSTAKAAN, perpus)
            startActivity(moveIntentWithData)
        }
    }
}