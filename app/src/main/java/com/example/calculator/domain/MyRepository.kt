package com.example.calculator.domain

import com.example.calculator.model.Order
import okhttp3.ResponseBody

interface MyRepository {
    fun getOrderList(): List<Order>
    fun fetchCurrentPrice(callback : (String?, Exception?) -> Unit)
}