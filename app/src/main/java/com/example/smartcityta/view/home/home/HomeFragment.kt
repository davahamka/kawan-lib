package com.example.smartcityta.view.home.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcityta.R
import com.example.smartcityta.databinding.FragmentHomeBinding
import com.example.smartcityta.datasource.network.models.PerpustakaanResponseItem
import com.example.smartcityta.view.home.detailperpustakaan.DetailPerpustakaanActivity

class HomeFragment : Fragment(), ListPerpustakaanAdapter.OnItemClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvPerpustakaan: RecyclerView = view.findViewById(R.id.rv_perpustakaan)

        viewModel.perpustakaans.observe(viewLifecycleOwner){perpustakaans->
            val listPerpustakaanAdapter = ListPerpustakaanAdapter(perpustakaans,this)
            rvPerpustakaan.adapter = listPerpustakaanAdapter
            rvPerpustakaan.layoutManager = LinearLayoutManager(requireContext())
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