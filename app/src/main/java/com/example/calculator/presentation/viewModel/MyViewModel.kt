package com.example.calculator.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.MyRepositoryImpl

class MyViewModel(private val repository: MyRepositoryImpl) : ViewModel() {

    private val _resultLiveData = MutableLiveData<String>()
    val resultLiveData: LiveData<String>
        get() = _resultLiveData

    private var i = 0
    val str = "Hello"
    private val _state = MutableLiveData<MyViewModelState>(MyViewModelState.Loading)
    val state: LiveData<MyViewModelState>
        get() = _state

    /*   init {
           fetchCurrentPrice()
       }*/

    fun wait1() {
        Thread {
            while (true) {
                try {
                    i = repository.getData(i)
                    _resultLiveData.postValue(i.toString())
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    fun getData() {
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
    }
}