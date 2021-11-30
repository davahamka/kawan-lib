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

class LoginViewModel :ViewModel() {
    private var _auths = MutableLiveData<List<AuthResponseItem>>()
    val auths:LiveData<List<AuthResponseItem>> = _auths

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> = _isLoading

    fun allAuths() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getAuths()
        client.enqueue(object : Callback<List<AuthResponseItem>> {
            override fun onResponse(call: Call<List<AuthResponseItem>>, response: Response<List<AuthResponseItem>>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _auths.value = response.body()
                } else {
                    Log.e("FAIL", "onFailure:${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<AuthResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e("FAIL", "onFailure: ${t.message.toString()}")
            }
        })
    }
}