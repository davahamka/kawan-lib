package com.example.smartcityta.view.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.smartcityta.R
import com.example.smartcityta.databinding.FragmentRegisterBinding
import com.example.smartcityta.datasource.network.models.AuthResponseItem

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val registerViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(RegisterViewModel::class.java)


        binding.buttonRegister.setOnClickListener{

            var validData = 0;
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            val ulangiPassword = binding.editTextUlangiPassword.text.toString()
            val nama = binding.editTextNama.text.toString()
            val email = binding.editTextEmail.text.toString()
            val alamat = binding.editTextAlamat.text.toString()


            if(username.isEmpty()){
                binding.editTextUsername.error = "Field masih kosong"
            } else if(username.length <4){
                binding.editTextUsername.error = "Minimal panjang 4 karakter"
            }
            else {
                validData+=1
            }

            if(password.isEmpty()){
                binding.editTextPassword.error = "Field masih kosong"
            }else if(password.length<4){
                binding.editTextPassword.error = "Minimal panjang 4 Karakter"
            }else{
                validData += 1
            }

            if(ulangiPassword.isEmpty()){
                binding.editTextUlangiPassword.error = "Field masih kosong"
            } else if(ulangiPassword != password){
                binding.editTextPassword.error = "Password tidak sama"
                binding.editTextUlangiPassword.error = "Password tidak sama"
            } else {
                validData +=1
            }

            if(nama.isEmpty()){
                binding.editTextNama.error = "Field masih kosong"
            } else {
               validData+=1
            }

            if(email.isEmpty()){
                binding.editTextEmail.error = "Field masih kosong"
            }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.editTextEmail.error = "Email tidak valid"
            }else {
                validData += 1
            }

            if(alamat.isEmpty()){
                binding.editTextAlamat.error = "Field masih ksong"
            } else {
                validData += 1
            }

            if(validData==6){
                binding.editTextUsername.text.clear()
                binding.editTextPassword.text.clear()
                binding.editTextUlangiPassword.text.clear()
                binding.editTextNama.text.clear()
                binding.editTextEmail.text.clear()
                binding.editTextAlamat.text.clear()
                registerViewModel.postRegister(AuthResponseItem(
                    password = password,
                    nama = nama,
                    username= username,
                    email = email,
                    alamat = alamat,
                    updatedAt = "",
                    createdAt = "",
                    id = 0
                ))

                val mSuccessRegisterFragment = SuccessRegisterFragment()
                val mFragmentManager = parentFragmentManager
                mFragmentManager.beginTransaction().apply {
                    replace(R.id.frame_container, mSuccessRegisterFragment, SuccessRegisterFragment::class.java.simpleName)
                    commit()
                }
            }


        }
    }


}