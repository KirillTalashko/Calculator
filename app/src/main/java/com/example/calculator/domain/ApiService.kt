package com.example.calculator.domain

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("v1/bpi/currentprice.json")
    fun fetchCurrentPrice(): Call<String>
}