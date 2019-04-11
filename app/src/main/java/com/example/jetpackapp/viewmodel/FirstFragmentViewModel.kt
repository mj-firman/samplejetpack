package com.example.jetpackapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackapp.model.LoginResponse
import com.example.jetpackapp.services.ApiClientAdapter
import com.example.jetpackapp.services.ApiServices
import retrofit2.Call
import retrofit2.Response

class FirstFragmentViewModel: ViewModel() {
    private var apiServices: ApiServices? = null
    private var apiClientAdapter: ApiClientAdapter? = null
    private var userLiveData = MutableLiveData<LoginResponse>()
    private var errorData = MutableLiveData<String>()


    init {
        apiClientAdapter = ApiClientAdapter()
        apiServices = apiClientAdapter?.getClient()?.create(ApiServices::class.java)
    }

    fun login() {
        val hashMap = HashMap<String, Any>().apply {
            put("grant_type", "client_credentials")
            put("client_id", 3)
            put("client_secret", "nurbHnXfijfIbUNTSXBZqMb5WqM5FcyIldQFBGXI")
        }
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

    fun getUser(): MutableLiveData<LoginResponse> {
        return userLiveData
    }

    fun getErrorLogin(): MutableLiveData<String> {
        return errorData
    }

}