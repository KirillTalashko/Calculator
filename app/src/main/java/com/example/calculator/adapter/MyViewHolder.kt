package com.example.calculator.adapter

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R

class MyViewHolder(itemLayoutView: View, private val clickListener: OnClickListener) :
    RecyclerView.ViewHolder(itemLayoutView) {
    private val text: TextView = itemLayoutView.findViewById(R.id.text_item)
    private val cardview: CardView = itemLayoutView.findViewById(R.id.card_view)

    fun bind(item: String) {
        text.text = item
        text.setOnClickListener {
            clickListener.onClickDelete(adapterPosition)
        }
        cardview.setOnLongClickListener {
            clickListener.onClickAdd()
            true
        }
    }
}