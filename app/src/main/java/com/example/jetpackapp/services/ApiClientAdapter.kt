package com.example.jetpackapp.services

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClientAdapter {
    var retrofit: Retrofit? = null
    var REQUEST_TIMEOUT: Long = 60
    var okHttpClient: OkHttpClient? = null
    var BASE_URL: String = "https://staging.dwisinergisakti.com/crmsystem/"

    fun getClient(): Retrofit? {
        initOkHttp()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    fun initOkHttp() {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        httpClient.addInterceptor(interceptor)

        httpClient.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("api-key", "esew36ansfkn7jma")
                    .addHeader("api-secret", "g4vcqgbkmf4ec3gvaeya9er9uf6umkqg")
                val request = requestBuilder.build()
                return chain.proceed(request)
            }

        })

        okHttpClient = httpClient.build()
    }

}