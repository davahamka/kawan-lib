package com.example.smartcityta.view.home.account

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.smartcityta.BuildConfig
import com.example.smartcityta.databinding.FragmentAccountBinding
import com.example.smartcityta.datasource.network.models.AuthResponseItem
import com.example.smartcityta.datasource.network.models.PerpustakaanResponseItem
import com.example.smartcityta.utils.UserPrefence
import com.example.smartcityta.view.home.HomeActivity
import com.example.smartcityta.view.home.detailperpustakaan.DetailPerpustakaanActivity
import com.example.smartcityta.view.home.mapperpustakaan.PerpustakaanMapsActivity
import com.example.smartcityta.view.login.LoginActivity

class AccountFragment : Fragment() {
    private lateinit var  userPrefence: UserPrefence
    private lateinit var accountViewModel: AccountViewModel
    private var _binding: FragmentAccountBinding? = null
    private lateinit var idUser:String
    private lateinit var dataAuth:AuthResponseItem

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


//        accountViewModel.auth.observe(viewLifecycleOwner, Observer{ auth->setAuthData(auth)})

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accountViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AccountViewModel::class.java)


        userPrefence = UserPrefence(view.context)



        accountViewModel.auth.observe(viewLifecycleOwner, Observer{ auth->setAuthData(auth)})
//        accountViewModel.auth.observe(viewLifecycleOwner, Observer{ auth->setAuthData(auth)})

        binding.buttonLogout.setOnClickListener{
            val moveIntent = Intent(activity, LoginActivity::class.java)
            startActivity(moveIntent)
        }

        binding.buttonMoveEditAccount.setOnClickListener{
                val moveIntentWithData = Intent(activity, EditAccountActivity::class.java)
                moveIntentWithData.putExtra(EditAccountActivity.EXTRA_AUTH, dataAuth)
                startActivity(moveIntentWithData)
        }

    }

    override fun onResume() {
        super.onResume()


        idUser = userPrefence.getUser().toString()

        if(idUser != null){
            Log.d("TESID",idUser)
            accountViewModel.findAuth(idUser)
        } else {
            Log.d("INFOOO", "User is null")
        }
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
        dataAuth = auth
        Glide.with(binding.root).load("https://ui-avatars.com/api/?size=72&name=${auth.nama}").into(binding.circleImageViewAccount)
    }
}

