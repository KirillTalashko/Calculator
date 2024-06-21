package com.example.calculator.domain

interface MyRepository {
    fun getData(count: Int): Int
    fun fetchCurrentPrice(callback: (String?, Exception?) -> Unit)
}