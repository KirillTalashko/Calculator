package com.example.calculator.model

sealed class StatusRequest {
    data class Error(val error: Exception) : StatusRequest()
    data class Success(val data: String) : StatusRequest()
    data object Loading: StatusRequest()
}