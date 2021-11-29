package com.example.smartcityta.view.home.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.smartcityta.databinding.FragmentAccountBinding
import com.example.smartcityta.datasource.network.models.AuthResponseItem

class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel
    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AccountViewModel::class.java)
        accountViewModel.auth.observe(viewLifecycleOwner, Observer{ auth->setAuthData(auth)})
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setAuthData(auth:AuthResponseItem){
        binding.textNama.text = auth.nama
        binding.textUsername.text = auth.username
        binding.textAlamat.text = auth.alamat
        binding.textEmail.text = auth.email
    }
}