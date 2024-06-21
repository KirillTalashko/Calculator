package com.example.calculator.domain

class MyRepositoryImpl : MyRepository {
    private val apiClient = CoinDeskApiClient()
    override fun getData(count: Int): Int {
        return (count + 1)
    }

    override fun fetchCurrentPrice(callback: (String?, Exception?) -> Unit) {
        apiClient.fetchCurrentPrice { response, error ->
            callback(response, error)
        }
    }

}