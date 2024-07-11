package com.example.calculator.model

enum class OrderStatus(val description: String){
    ORDERED("Cоздан") {
        override fun nextStatus(): OrderStatus = DELIVERED
    },
    DELIVERED("Доставлен") {
        override fun nextStatus(): OrderStatus {
            throw IllegalStateException("Доставленный заказ не может иметь следующий статус")
        }
    };
    abstract fun nextStatus():OrderStatus
}