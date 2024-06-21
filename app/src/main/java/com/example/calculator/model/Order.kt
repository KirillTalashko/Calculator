package com.example.calculator.model

import android.util.Log

class Order(var status: OrderStatus) {
    fun proceedToNextStatus() {
        status = status.nextStatus()
    }

    fun printStatus() {
        Log.i("YouTag", "Current order status: ${status.description}")
    }

    fun updateStatus(newStatus: OrderStatus) {
        status = newStatus
    }
}