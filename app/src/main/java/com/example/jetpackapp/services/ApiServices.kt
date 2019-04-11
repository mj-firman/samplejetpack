package com.example.jetpackapp.services

import com.example.jetpackapp.model.HeroResponse
import com.example.jetpackapp.model.LoginResponse
import com.example.jetpackapp.model.NewsResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiServices {

    @POST("oauth/token")
    @FormUrlEncoded
    fun login(@FieldMap body: HashMap<String, Any>?): Call<LoginResponse>

    @POST("api/v1/cust/news/list")
    fun getNews(@Header("Authorization") token: String): Call<NewsResponse>

    @GET("marvel")
    fun getHeros(): Call<List<HeroResponse>>

}