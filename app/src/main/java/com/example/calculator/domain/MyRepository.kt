package com.example.calculator.domain

import com.example.calculator.model.Order

interface MyRepository {
    fun getOrderList(): List<Order>
}