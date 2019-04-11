package com.example.jetpackapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackapp.model.LoginResponse
import com.example.jetpackapp.services.ApiClientAdapter
import com.example.jetpackapp.services.ApiServices
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import java.sql.Struct

class FirstFragmentViewModel: ViewModel() {
    var apiServices: ApiServices? = null
    var apiClientAdapter: ApiClientAdapter? = null
    var userLiveData = MutableLiveData<LoginResponse>()
    var errorData = MutableLiveData<String>()

    init {
        apiClientAdapter = ApiClientAdapter()
        apiServices = apiClientAdapter?.getClient()?.create(ApiServices::class.java)
    }

    public fun login() {
        var hashMap = HashMap<String, Any>()
        hashMap.put("grant_type", "client_credentials")
        hashMap.put("client_id", 3)
        hashMap.put("client_secret", "nurbHnXfijfIbUNTSXBZqMb5WqM5FcyIldQFBGXI")
        apiServices?.login(hashMap)?.enqueue(object: retrofit2.Callback<LoginResponse>{
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                errorData.value = t.message
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.code() == 200) {
                    userLiveData.value = response.body()
                } else {
                    val errorBody = response.errorBody()
                    errorData.value = errorBody.toString()
                }
            }
        })
    }

    public fun getUser(): MutableLiveData<LoginResponse> {
        return userLiveData
    }

    public fun getErrorLogin(): MutableLiveData<String> {
        return errorData
    }

}