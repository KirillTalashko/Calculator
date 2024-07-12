package com.example.calculator.presentation.adapter

import com.example.calculator.model.Order

interface OnClickListener {
    fun onClickDelete(position: Int)
    fun onClickAdd(position: Int)
    fun onClickLoading(item: Order, adapterPosition: Int)
}
