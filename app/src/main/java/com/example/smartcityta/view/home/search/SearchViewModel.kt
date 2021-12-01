package com.example.smartcityta.view.home.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartcityta.datasource.network.ApiConfig
import com.example.smartcityta.datasource.network.models.AuthResponseItem
import com.example.smartcityta.datasource.network.models.PerpustakaanResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val _perpustakaans = MutableLiveData<List<PerpustakaanResponseItem>>()
    val perpustakaans: LiveData<List<PerpustakaanResponseItem>> = _perpustakaans

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading



    init {
        getPerpustakaans()
    }

    fun getPerpustakaans() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPerpustakaans()
        client.enqueue(object : Callback<List<PerpustakaanResponseItem>> {
            override fun onResponse(call: Call<List<PerpustakaanResponseItem>>, response: Response<List<PerpustakaanResponseItem>>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _perpustakaans.value = response.body()
                } else {
                    Log.e("FAIL", "onFailure:${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<PerpustakaanResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e("FAIL", "onFailure: ${t.message.toString()}")
            }
        })
    }
}