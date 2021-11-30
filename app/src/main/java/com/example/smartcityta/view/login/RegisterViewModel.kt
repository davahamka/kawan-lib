package com.example.smartcityta.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartcityta.datasource.network.ApiConfig
import com.example.smartcityta.datasource.network.models.AuthResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel: ViewModel() {
    private val _auth = MutableLiveData<AuthResponseItem>()
    val auth: LiveData<AuthResponseItem> = _auth

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
        private const val AUTH_ID = "2"
    }


    fun postRegister(auths:AuthResponseItem){
        _isLoading.value = true
        val client = ApiConfig.getApiService().createAuth(auths)

        client.enqueue(object : Callback<AuthResponseItem>{
            override fun onResponse(
                call: Call<AuthResponseItem>,
                response: Response<AuthResponseItem>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    Log.e("COBA REGISTER","berhasil register!!!")
                } else {
                    Log.e(RegisterViewModel.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<AuthResponseItem>, t: Throwable) {
                _isLoading.value = false
                Log.e(RegisterViewModel.TAG, "onFailure: ${t.message.toString()}")
            }
        }
        )
    }

}