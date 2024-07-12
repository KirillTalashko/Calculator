package com.example.calculator.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.domain.MyRepositoryImpl

class MyViewModelFactory(private val repository: MyRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelOrder::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelOrder(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
