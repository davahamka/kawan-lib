package com.example.smartcityta.view.home.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartcityta.BuildConfig.BASE_URL_CMS
import com.example.smartcityta.R
import com.example.smartcityta.datasource.network.models.PerpustakaanResponseItem

class SearchPerpustakaanAdapter(private val listPerpustakaan:List<PerpustakaanResponseItem>, private val onItemClickListener: SearchPerpustakaanAdapter.OnItemClickListener)
    :RecyclerView.Adapter<SearchPerpustakaanAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view:View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_perpustakaan, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val perpus = listPerpustakaan[position]
        holder.textNamaPerpustakaan.text = perpus.nama
        holder.textAlamatPerpustakaan.text = perpus.alamat
        holder.textJumlahPerpustakaan.text = "${perpus.totalPengunjung} Pengunjung"
        Glide.with(holder.itemView.context).load("${BASE_URL_CMS}${perpus.gambar[0].url}").into(holder.imageViewPerpustakaan)

        holder.buttonLihatDetail.setOnClickListener{
            onItemClickListener.onItemClicked(listPerpustakaan[position])
        }
    }

    override fun getItemCount(): Int {
        return listPerpustakaan.size
    }

    class ListViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        var textNamaPerpustakaan:TextView = itemView.findViewById(R.id.textNamaPerpustakaan)
        var textAlamatPerpustakaan:TextView = itemView.findViewById(R.id.textAlamatPerpustakaan)
        var textJumlahPerpustakaan:TextView = itemView.findViewById(R.id.textPengunjungPerpustakaan)
        var imageViewPerpustakaan:ImageView = itemView.findViewById(R.id.imageViewPerpustakaan)
        var buttonLihatDetail:Button = itemView.findViewById(R.id.buttonLihatDetail)


    }

    interface OnItemClickListener{
        fun onItemClicked(perpustakaan:PerpustakaanResponseItem)
    }

}