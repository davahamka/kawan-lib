package com.example.smartcityta.view.home.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcityta.R
import com.example.smartcityta.databinding.FragmentSearchBinding
import com.example.smartcityta.datasource.network.models.PerpustakaanResponseItem
import com.example.smartcityta.view.home.detailperpustakaan.DetailPerpustakaanActivity
import com.example.smartcityta.view.home.home.HomeViewModel
import com.example.smartcityta.view.home.home.ListPerpustakaanAdapter
import com.example.smartcityta.view.home.home.SearchPerpustakaanAdapter
import com.example.smartcityta.view.home.home.SearchViewModel

class SearchFragment : Fragment(),SearchPerpustakaanAdapter.OnItemClickListener {

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null
    private val viewModel: HomeViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvPerpustakaan: RecyclerView = view.findViewById(R.id.rv_search_perpustakaan)

        binding.etSearchPerpustakaan.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                Log.d("Logg","onTextChanged: ${s}")

            }


        })


        binding.btnSearch.setOnClickListener{
           val searchValue = binding.etSearchPerpustakaan.text.toString()

            if(searchValue.length <= 0){

            }

            viewModel.perpustakaans.observe(viewLifecycleOwner){perpustakaans->


                val perpusYangDicari = perpustakaans.filter { perpustakaan->
                    perpustakaan.nama.contains(searchValue,ignoreCase = true)
                }

                if(searchValue.length <=0){
                    Toast.makeText(activity, "Harap isi terlebih dahulu",Toast.LENGTH_SHORT).show()
                    val listPerpustakaanAdapter = SearchPerpustakaanAdapter(perpusYangDicari,this)
                    rvPerpustakaan.adapter = null
                    rvPerpustakaan.layoutManager = LinearLayoutManager(requireContext())
                }
                else if(perpusYangDicari.size > 0){

                    val listPerpustakaanAdapter = SearchPerpustakaanAdapter(perpusYangDicari,this)
                    rvPerpustakaan.adapter = listPerpustakaanAdapter
                    rvPerpustakaan.layoutManager = LinearLayoutManager(requireContext())
                }else {
                    Toast.makeText(activity, "Data tidak ditemukan",Toast.LENGTH_SHORT).show()
                }
            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(perpustakaan: PerpustakaanResponseItem) {
        val moveIntentWithData = Intent(activity, DetailPerpustakaanActivity::class.java)
        moveIntentWithData.putExtra(DetailPerpustakaanActivity.EXTRA_PERPUSTAKAAN, perpustakaan)
        startActivity(moveIntentWithData)
    }
}