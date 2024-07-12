package com.example.calculator.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import com.example.calculator.databinding.ItemListBinding

class Adapter(
    private val recyclerList: MutableList<String>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = ItemListBinding.inflate(layoutInflater,parent,false)
            return MyViewHolder(view, onClickListener)
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.i("Tag", "${recyclerList[position]}")
    }


}