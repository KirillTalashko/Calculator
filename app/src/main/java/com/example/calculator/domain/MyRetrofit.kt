package com.example.calculator.domain

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 class MyRetrofit {
    private var retrofit : Retrofit? = null
    private val baseUrl = "https://api.coindesk.com/"

    fun getClient(): ApiService{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(ApiService::class.java)
    }
}