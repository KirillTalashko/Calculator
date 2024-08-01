package com.example.calculator.domain

import com.example.calculator.model.Order
import retrofit2.Call

interface MyRepository {
    fun getOrderList(): List<Order>
    fun fetchCurrentPrice(callback : (String?, Exception?) -> Unit)
    fun fetchCurrentPriceRetrofit(): Call<String>
}