package com.example.calculator.domain

import retrofit2.Call

interface MyRepository {
    fun getData(count: Int): Int
    fun fetchCurrentPrice(callback: (String?, Exception?) -> Unit)
    fun fetchCurrentPriceRetrofit(): Call<String>

}