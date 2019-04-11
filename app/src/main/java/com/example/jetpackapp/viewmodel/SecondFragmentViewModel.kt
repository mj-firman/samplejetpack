package com.example.jetpackapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackapp.model.NewsResponse
import com.example.jetpackapp.services.ApiClientAdapter
import com.example.jetpackapp.services.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondFragmentViewModel: ViewModel() {
    private var apiServices: ApiServices? = null
    private var apiClientAdapter: ApiClientAdapter? = null
    private var newsLiveData = MutableLiveData<List<NewsResponse.NewsEntity>>()
    private var errorData = MutableLiveData<String>()

    init {
        apiClientAdapter = ApiClientAdapter()
        apiServices = apiClientAdapter?.getClient()?.create(ApiServices::class.java)
    }

    public fun getNews(token: String): MutableLiveData<List<NewsResponse.NewsEntity>> {
        callNews(token)
        return newsLiveData
    }

    private fun callNews(token: String) {
        apiServices?.getNews(token)?.enqueue(object: Callback<NewsResponse>{
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                errorData.value = t.message
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if(response.code() == 200) {
                    val results = response.body()?.result?.news
                    if(results!=null) {
                        newsLiveData.value = results
                    }
                } else {
                    errorData.value = "Error load data!"
                }
            }
        })
    }

    public fun getErrorData(): MutableLiveData<String> {
        return errorData
    }


}