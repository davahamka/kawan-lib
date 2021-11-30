package com.example.smartcityta.view.home.mapperpustakaan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcityta.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.smartcityta.databinding.ActivityPerpustakaanMapsBinding
import com.example.smartcityta.datasource.network.models.PerpustakaanResponseItem
import com.example.smartcityta.view.home.detailperpustakaan.DetailPerpustakaanActivity

class PerpustakaanMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityPerpustakaanMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerpustakaanMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val perpus = intent.getParcelableExtra<PerpustakaanResponseItem>(DetailPerpustakaanActivity.EXTRA_PERPUSTAKAAN) as PerpustakaanResponseItem

        val letakPerpus = LatLng(perpus.lat, perpus.long)
        mMap.addMarker(MarkerOptions().position(letakPerpus).title(perpus.nama))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(letakPerpus, 17.0f))

    }

    companion object {
        const val EXTRA_PERPUSTAKAAN = "extra_perpustakaan"
    }
}