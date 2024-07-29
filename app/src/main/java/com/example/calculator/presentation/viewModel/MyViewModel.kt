package com.example.calculator.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.MyRepository
import com.example.calculator.domain.MyRepositoryImpl
import com.example.calculator.presentation.Action
import com.example.calculator.extentions.log
import com.example.calculator.model.StatusRequest

class MyViewModel(private val repository: MyRepository) : ViewModel() {

    val resultViewModel = MutableLiveData<String>()
    val actionList = MutableLiveData<String>()
    val str = StringBuilder()
    private var firstNum = 0.0
    private var secondNum = 0.0
    var action = Action.DIVIDE
    private val _stateRequest = MutableLiveData<StatusRequest>(StatusRequest.Loading)
    val stateRequest : LiveData<StatusRequest>
        get() = _stateRequest


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

    fun getData(){
        _stateRequest.value = StatusRequest.Loading
        var count = 3
        var key = true
        Thread{
            while(key){
                try {
                    if (count >= 0) {
                        Thread.sleep(2000)
                        _stateRequest.postValue(StatusRequest.Success("Загрузка данных $count"))
                        count--
                    } else {
                        key = false
                        fetchCurrentPrice()
                    }
                }catch (e: InterruptedException){
                    _stateRequest.postValue(StatusRequest.Error(e))
                    e.printStackTrace()
                }
            }
        }.start()
    }

     private fun fetchCurrentPrice(){
         repository.fetchCurrentPrice { response, exception ->
             if (exception != null)
                 throw InterruptedException(exception.message)
             else if (response != null)
                 _stateRequest.postValue(StatusRequest.Success(response))
         }
    }
}