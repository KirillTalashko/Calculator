package com.example.calculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private  val recyclerList: List<String>): RecyclerView.Adapter<Adapter.MyViewHolder>() {

    class MyViewHolder(itemLayout : View) : RecyclerView.ViewHolder(itemLayout) {
        private val text : TextView = itemLayout.findViewById(R.id.text_item)
        fun bind(recyclerList: String){
            text.text = recyclerList
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent ,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(recyclerList[position])
    }
}