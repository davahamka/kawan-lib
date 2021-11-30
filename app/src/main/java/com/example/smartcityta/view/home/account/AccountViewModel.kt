package com.example.smartcityta.view.home.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartcityta.datasource.network.ApiConfig
import com.example.smartcityta.datasource.network.models.AuthResponseItem
import com.example.smartcityta.utils.UserPrefence
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountViewModel : ViewModel() {

    private val _auth = MutableLiveData<AuthResponseItem>()
    val auth: LiveData<AuthResponseItem> = _auth

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
        private const val USER_ID = "2"
    }


    fun findAuth(id:String){
       _isLoading.value = true

       val client = ApiConfig.getApiService().getAuth(id)
       client.enqueue(object : Callback<AuthResponseItem>{
           override fun onResponse(
               call: Call<AuthResponseItem>,
               response: Response<AuthResponseItem>
           ) {
               _isLoading.value = false
               if(response.isSuccessful){
                   _auth.value = response.body()
               } else {
                   Log.e(TAG, "onFailure: ${response.message()}")
               }
           }

           override fun onFailure(call: Call<AuthResponseItem>, t: Throwable) {
               _isLoading.value = false
               Log.e(TAG, "onFailure: ${t.message.toString()}")
           }
       }
       )
   }
}