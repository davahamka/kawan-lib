package com.example.smartcityta.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartcityta.datasource.network.ApiConfig
import com.example.smartcityta.datasource.network.models.AuthResponseItem
import com.example.smartcityta.utils.Response
import retrofit2.Call
import retrofit2.Callback

class EditAccountViewModel: ViewModel() {
    private val _auth = MutableLiveData<Response<AuthResponseItem>>()
    val auth: LiveData<Response<AuthResponseItem>> = _auth


    companion object {
        private const val TAG = "MainViewModel"
    }


    fun postEdit(nAuth:AuthResponseItem){
        val client = ApiConfig.getApiService().editAuth(nAuth.id.toString(),nAuth)
        _auth.postValue(Response.loading(null))
        client.enqueue(object : Callback<AuthResponseItem>{
            override fun onResponse(
                call: Call<AuthResponseItem>,
                response: retrofit2.Response<AuthResponseItem>
            ) {
                if(response.isSuccessful){
                    _auth.postValue(Response.success(response.body()))
                }
            }

            override fun onFailure(call: Call<AuthResponseItem>, t: Throwable) {
               _auth.postValue(Response.error("GAGAL",null))
            }

        })

    }

}