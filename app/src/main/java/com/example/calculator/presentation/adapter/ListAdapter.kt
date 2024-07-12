package com.example.calculator.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.databinding.ItemListBinding
import com.example.calculator.databinding.ItemListEmptyBinding
import com.example.calculator.model.Order

class ListAdapter(private val clickListener: OnClickListener) :
    ListAdapter<Order, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(oldItem: Order, newItem: Order) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Order, newItem: Order) =
                oldItem == newItem
        }
        private const val VIEW_HOLDER = 0
        private const val VIEW_HOLDER_EMPTY = 1

    }

    override fun getItemCount(): Int {
        return if (currentList.isEmpty()) {
            1
        } else {
            currentList.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList.size == 0){
            VIEW_HOLDER_EMPTY
        } else {
            VIEW_HOLDER
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_HOLDER_EMPTY -> {
                val view = ItemListEmptyBinding.inflate(layoutInflater,parent,false)
                MyViewHolderEmpty(view)
            }
            else -> {
                val view = ItemListBinding.inflate(layoutInflater,parent,false)
                MyViewHolder(view,clickListener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (currentList.isEmpty()) {
            if (holder is MyViewHolderEmpty) {
                holder.bind()
            }
        } else {
            val item = getItem(position)
            when (holder) {
                is MyViewHolder -> holder.bind(item,itemCount)
            }
        }
    }
}