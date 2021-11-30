package com.example.smartcityta.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.smartcityta.R
import com.example.smartcityta.databinding.FragmentLoginBinding
import com.example.smartcityta.utils.UserPrefence
import com.example.smartcityta.view.home.HomeActivity
import com.example.smartcityta.view.home.MainActivity

class LoginFragment : Fragment(), View.OnClickListener {
    private var username: String?= null
    private var password: String?= null
    private var _binding:FragmentLoginBinding?=null
    private val binding get() = _binding!!

    private val viewModel:LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container,false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)

        observeAuth()
    }

    private fun observeAuth() {
        viewModel.auths.observe(viewLifecycleOwner){ auths->
            val checkAuth = auths.singleOrNull { auth->
                auth.username == username && auth.password == password
            }

            if(checkAuth != null){
                val userPrefence = UserPrefence(requireContext())

                userPrefence.setUser(checkAuth.id.toString())

                activity?.finishAffinity()
                val moveIntent = Intent(activity, HomeActivity::class.java)
                startActivity(moveIntent)
            } else {
                binding.editTextUsername.error = "Username salah"
                binding.editTextPassword.error = "Password salah"
            }
        }

    }


    override fun onClick(v: View) {
        if(v.id == R.id.btn_register){
            val mRegisterFragment = RegisterFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frame_container, mRegisterFragment, RegisterFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        } else if(v.id == R.id.btn_login){
            var validData = 0


            username = binding.editTextUsername.text.toString()
            password = binding.editTextPassword.text.toString()

            if(username.isNullOrEmpty()){
                binding.editTextUsername.error = "Username kosong"

            } else {
                validData +=1
            }

            if(password.isNullOrEmpty()){
                binding.editTextPassword.error = "Password kosong"

            } else {
                validData +=1
            }

            if(validData == 2){
                binding.editTextUsername.text.clear()
                binding.editTextPassword.text.clear()
                viewModel.allAuths()
            }

        }

    }
}