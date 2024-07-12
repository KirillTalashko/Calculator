package com.example.calculator.domain

import com.example.calculator.model.Order
import com.example.calculator.model.OrderStatus

class MyRepositoryImpl(): MyRepository {

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
}
//Order("Заказ_7", OrderStatus.ORDERED),
//        Order("Заказ_8", OrderStatus.ORDERED),
//        Order("Заказ_9", OrderStatus.ORDERED),
//        Order("Заказ_10", OrderStatus.ORDERED),
//        Order("Заказ_11", OrderStatus.ORDERED),
//        Order("Заказ_12", OrderStatus.ORDERED),
//        Order("Заказ_13", OrderStatus.ORDERED),
//        Order("Заказ_14", OrderStatus.ORDERED),
//        Order("Заказ_15", OrderStatus.ORDERED)