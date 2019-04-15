package com.example.jetpackapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackapp.model.HeroResponse
import com.example.jetpackapp.services.ApiClientAdapterV2
import com.example.jetpackapp.services.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdFragmentViewModel: ViewModel() {
    private var apiServices: ApiServices? = null
    private var apiClientAdapterV2: ApiClientAdapterV2
    private var heroList = MutableLiveData<List<HeroResponse>>()


    init {
        apiClientAdapterV2 = ApiClientAdapterV2()
        apiServices = apiClientAdapterV2.getClient()?.create(ApiServices::class.java)
    }

    fun getHeroes(): MutableLiveData<List<HeroResponse>>{
        apiServices?.getHeros()?.enqueue(object: Callback<List<HeroResponse>>{
            override fun onFailure(call: Call<List<HeroResponse>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<HeroResponse>>, response: Response<List<HeroResponse>>) {
                if(response.code() == 200) {
                    heroList.value = response.body()
                }
            }
        })
        return heroList
    }
}