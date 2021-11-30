package com.example.smartcityta.view.home.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.smartcityta.R
import com.example.smartcityta.databinding.ActivityEditAccountBinding
import com.example.smartcityta.view.home.HomeActivity

class EditAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonExitEdit.setOnClickListener{
            val moveIntent = Intent(this, HomeActivity::class.java)
            startActivity(moveIntent)
        }
        binding.buttonEditAccount.setOnClickListener{
            val username = binding.textEditAccountUsername.text.toString()
            val email = binding.textEditAccountEmail.text.toString()
            val nama = binding.textEditAccountNama.text.toString()
            val alamat = binding.textEditAccountAlamat.text.toString()
            Log.d("CheckEdit", "${username} ${email} ${nama} ${alamat}")
        }

    }








}