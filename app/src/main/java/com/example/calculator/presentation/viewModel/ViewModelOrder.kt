package com.example.calculator.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.MyRepository
import com.example.calculator.domain.MyRepositoryImpl
import com.example.calculator.model.Order
import com.example.calculator.model.OrderStatus

class ViewModelOrder(private val repository: MyRepository) : ViewModel() {

    private val _order = MutableLiveData<List<Order>>()
    val order: LiveData<List<Order>>
        get() = _order

    fun getOrder(){
        _order.value = repository.getOrderList()
    }

    fun deleteOrder(position : Int){
        val list = mutableListOf<Order>()
        list.addAll(_order.value?: emptyList())
        list.removeAt(position)
        _order.value = list
    }
    fun addOrder(order: Order){
        val list = mutableListOf<Order>()
        list.addAll(_order.value?: emptyList())
        list.add(order)
        _order.value = list
    }
    fun loadingOrder(item: Order, adapterPosition: Int){
        val list = mutableListOf<Order>()
        list.addAll(_order.value?: emptyList())
        if(item.ordered == OrderStatus.ORDERED) {
            list[adapterPosition] = Order(item.description, item.ordered.nextStatus())
        }
        _order.value = list
    }
}