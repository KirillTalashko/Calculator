package com.example.calculator.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R

class Adapter(
    private val recyclerList: MutableList<String>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
        return MyViewHolder(view, onClickListener)
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(recyclerList[position])
        Log.i("Tag", "${recyclerList[position]}")
    }

    fun delete(position: Int) {
        recyclerList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun add() {
        recyclerList.add("Element_$itemCount")
        notifyItemInserted(itemCount)
    }

}