package com.example.calculator.presentation.viewModel

sealed class MyViewModelState {
    data class Success(val data: String) : MyViewModelState()
    data class Error(val error: Throwable) : MyViewModelState()
    data object Loading : MyViewModelState()
}