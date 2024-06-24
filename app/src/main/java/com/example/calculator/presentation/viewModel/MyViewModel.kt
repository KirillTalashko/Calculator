package com.example.calculator.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.MyRepositoryImpl
import com.example.calculator.presentation.Action

class MyViewModel(private val repository: MyRepositoryImpl) : ViewModel() {
    val str = StringBuilder()
    val resultLiveData = MutableLiveData<String>()
    var firstNum = 0.0
    var secondNum = 0.0
    var action = Action.DIVIDE

    val actionLiveData = MutableLiveData<String>()
    private var i = 0
    private val _state = MutableLiveData<MyViewModelState>(MyViewModelState.Loading)
    val state: LiveData<MyViewModelState>
        get() = _state


    fun defineNumbers() {
        val input = str.toString()
        val numbers =
            input.split(Regex("[+-/*]"))
        if (numbers.size >= 2) {
            firstNum = numbers[0].toDouble()
            secondNum = numbers[1].toDouble()
            actionLiveData.value = input[numbers[0].length].toString()
            // action = defineAction(input[numbers[0].length].toString())
        } else {
            Log.i("youTag", "Не правильно ввели числа")
        }
    }


    fun calculate() {
        try {
            val result = when (action) {
                Action.DIVIDE -> firstNum / secondNum
                Action.MULTIPLI -> firstNum * secondNum
                Action.MINUS -> firstNum - secondNum
                Action.PLUS -> firstNum + secondNum
            }
            if (result == result.toInt().toDouble()) {
                resultLiveData.value = result.toInt().toString()
            } else {
                resultLiveData.value = result.toString()
            }
        } catch (e: Exception) {
            Log.i("youTag", " ошибка ${e.message.toString()}")
        }
    }
    /*   init {
           fetchCurrentPrice()
       }*/

    /*    fun wait1() {
            Thread {
                while (true) {
                    try {
                        i = repository.getData(i)
                        resultLiveData.postValue(i.toString())
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }.start()
        }*/

    /*    fun getData() {
            _state.value = MyViewModelState.Loading
            var key = true
            var count = 0
            Thread {
                while (key) {
                    try {
                        if (count < 3) {
                            Thread.sleep(5000)
                            _state.postValue(MyViewModelState.Success("Успешная загрузка данных $count"))
                            count++
                        } else {
                            key = false
                            throw InterruptedException("Ошибка загрузки данных")
                        }
                    } catch (e: InterruptedException) {
                        _state.postValue(MyViewModelState.Error(e))
                        e.printStackTrace()
                    }
                }
            }.start()
        }

        fun fetchCurrentPrice() {
            _state.value = MyViewModelState.Loading
            Thread {
                repository.fetchCurrentPrice { response, exception ->
                    if (exception != null) {
                        _state.postValue(MyViewModelState.Error(exception))
                    } else if (response != null) {
                        _state.postValue(MyViewModelState.Success(response))
                    }
                }
            }.start()
        }*/
}