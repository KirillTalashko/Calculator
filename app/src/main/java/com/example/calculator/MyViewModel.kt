package com.example.calculator

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.extentions.log
import kotlin.math.log

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
            } else {
                resultViewModel.value = result.toString()
            }
        } catch (e: Exception) {
            e.message?.log()
        }
    }
}