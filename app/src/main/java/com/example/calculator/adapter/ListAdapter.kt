package com.example.calculator.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.databinding.ItemListAddBinding
import com.example.calculator.databinding.ItemListBinding
import com.example.calculator.databinding.ItemListDeleteBinding
import com.example.calculator.databinding.ItemListEmptyBinding

class ListAdapter(private val clickListener: OnClickListener) :
    ListAdapter<String, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String) =
                oldItem == newItem
        }
        private const val VIEW_HOLDER = 0
        private const val VIEW_HOLDER_ADD = 1
        private const val VIEW_HOLDER_DELETE = 2
        private const val VIEW_HOLDER_EMPTY = 3

    }

    override fun getItemCount(): Int {
        return if (currentList.isEmpty()) {
            1
        } else {
            currentList.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList.size == 1){
            VIEW_HOLDER
        } else {
            if (currentList.isEmpty()) {
                VIEW_HOLDER_EMPTY
            } else {
                if (position % 2 == 0) {
                    VIEW_HOLDER_ADD
                } else {
                    VIEW_HOLDER_DELETE
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_HOLDER_EMPTY -> {
                val view = ItemListEmptyBinding.inflate(layoutInflater,parent,false)
                MyViewHolderEmpty(view)
            }

            VIEW_HOLDER_ADD -> {
                val view = ItemListAddBinding.inflate(layoutInflater,parent,false)
                MyViewHolderAdd(view,clickListener)
            }
            VIEW_HOLDER_DELETE -> {
                val view = ItemListDeleteBinding.inflate(layoutInflater,parent,false)
                MyViewHolderDelete(view,clickListener)

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
                is MyViewHolder -> holder.bind(item)
                is MyViewHolderDelete -> holder.bind(item)
                is MyViewHolderAdd -> holder.bind(item)
            }
        }
    }
}