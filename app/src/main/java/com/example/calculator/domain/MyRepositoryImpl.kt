package com.example.calculator.domain

import com.example.calculator.model.Order
import com.example.calculator.model.OrderStatus
import retrofit2.Call

class MyRepositoryImpl : MyRepository {

    private val apiClient = CoinDeskApiClient()

    val orderStatusList = listOf(
        Order("Заказ_1", OrderStatus.ORDERED),
        Order("Заказ_2", OrderStatus.ORDERED),
        Order("Заказ_3", OrderStatus.ORDERED),
        Order("Заказ_4", OrderStatus.ORDERED),
        Order("Заказ_5", OrderStatus.ORDERED),
        Order("Заказ_6", OrderStatus.ORDERED),
    )

    override fun getOrderList(): List<Order> {
        return orderStatusList
    }
    override fun fetchCurrentPrice(callback : (String?, Exception?) -> Unit){
        apiClient.fetchCurrentPrice{ response, exception ->
            callback(response,exception)
        }
    }

    override fun fetchCurrentPriceRetrofit(): Call<String>{
        TODO()
    }


}
