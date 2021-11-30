package com.example.smartcityta.view.home.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.smartcityta.R
import com.example.smartcityta.databinding.ActivityEditAccountBinding
import com.example.smartcityta.datasource.network.models.AuthResponseItem
import com.example.smartcityta.utils.Status
import com.example.smartcityta.view.home.HomeActivity
import com.example.smartcityta.view.home.home.HomeViewModel
import com.example.smartcityta.view.login.EditAccountViewModel

class EditAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditAccountBinding

    private val viewModel: EditAccountViewModel by viewModels()

    private lateinit var  auth:AuthResponseItem

    companion object {
        const val EXTRA_AUTH = "extra_auth"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth =  intent.getParcelableExtra<AuthResponseItem>(EXTRA_AUTH) as AuthResponseItem

        binding.textEditAccountEmail.setText(auth.email)
        binding.textEditAccountNama.setText(auth.nama)
        binding.textEditAccountAlamat.setText(auth.alamat)

        binding.buttonExitEdit.setOnClickListener{
            finish()
        }
        binding.buttonEditAccount.setOnClickListener{
            val email = binding.textEditAccountEmail.text.toString()
            val nama = binding.textEditAccountNama.text.toString()
            val alamat = binding.textEditAccountAlamat.text.toString()


            viewModel.postEdit(AuthResponseItem(
                password = auth.password,
                nama = nama,
                username= auth.username,
                email = email,
                alamat = alamat,
                updatedAt = "",
                createdAt = "",
                id = auth.id
            ))

            viewModel.auth.observe(this){auth->
                if (auth.status==Status.SUCCESS){
                    Toast.makeText(this,"Akun berhasil diubah",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }










}