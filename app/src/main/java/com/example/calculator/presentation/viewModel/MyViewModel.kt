package com.example.calculator.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.MyRepository
import com.example.calculator.domain.MyRepositoryImpl
import com.example.calculator.presentation.Action
import com.example.calculator.extentions.log

class MyViewModel() : ViewModel() {

    val resultViewModel = MutableLiveData<String>()
    val actionList = MutableLiveData<String>()
    val str = StringBuilder()
    private var firstNum = 0.0
    private var secondNum = 0.0
    var action = Action.DIVIDE


    fun defineNumber() {
        val input = str.toString()
        val numbers =
            input.split(Regex("[+-/*]"))
        if (numbers.size >= 2) {
            firstNum = numbers[0].toDouble()
            secondNum = numbers[1].toDouble()
            actionList.value = input[numbers[0].length].toString()
        } else {
            "Wrong numbers".log()
        }
    }

    fun calculate() {
        try {
            val result = when (action) {
                Action.DIVIDE -> firstNum / secondNum
                Action.MULTIPLI -> firstNum * secondNum
                Action.MINUS -> firstNum - secondNum
                Action.PLUS -> firstNum + secondNum
                else -> {
                    throw Exception("Error action")
                }
            }
            if (result == result.toInt().toDouble()) {
                resultViewModel.value = result.toInt().toString()
                str.clear()
            } else {
                resultViewModel.value = result.toString()
                str.clear()
            }
        } catch (e: Exception) {
            e.message?.log()
        }
    }
}