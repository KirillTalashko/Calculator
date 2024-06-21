package com.example.calculator.model

enum class OrderStatus(val description: String) {
    ORDERED("Заказ создан") {
        override fun nextStatus(): OrderStatus = SHIPPED
    },
    SHIPPED("Заказ отправлен") {
        override fun nextStatus(): OrderStatus = DELIVERED
    },
    DELIVERED("Заказ доставлен") {
        override fun nextStatus(): OrderStatus {
            throw IllegalStateException("Доставленный заказ не может иметь следующий статус")
        }
    },
    CANCELLED("Заказ отменен") {
        override fun nextStatus(): OrderStatus {
            throw IllegalStateException("Отмененный заказ не может иметь следующий статус")
        }
    };

    abstract fun nextStatus(): OrderStatus

    fun changeStatus(newStatus: OrderStatus): OrderStatus {
        return newStatus
    }
}