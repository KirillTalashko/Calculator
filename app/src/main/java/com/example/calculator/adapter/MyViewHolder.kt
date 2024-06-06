package com.example.calculator.adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.databinding.ItemListAddBinding
import com.example.calculator.databinding.ItemListBinding
import com.example.calculator.databinding.ItemListDeleteBinding
import com.example.calculator.databinding.ItemListEmptyBinding


class MyViewHolder(
    private val binding: ItemListBinding,
    private val clickListener: OnClickListener
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.textItem.text = item
        binding.imageDelete.setOnClickListener {
            clickListener.onClickDelete(adapterPosition)
        }
        binding.imageAdd.setOnClickListener {
            clickListener.onClickAdd()
        }
    }
}

class MyViewHolderDelete(
    private val binding: ItemListDeleteBinding,
    private val clickListener: OnClickListener
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.textItem.text = item
        binding.imageDelete.setOnClickListener {
            clickListener.onClickDelete(adapterPosition)
        }
    }
}

    class MyViewHolderAdd(
        private val binding: ItemListAddBinding,
        private val clickListener: OnClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.textItem.text = item
            binding.imageAdd.setOnClickListener {
                clickListener.onClickAdd()
            }
        }
    }

    class MyViewHolderEmpty(private val binding: ItemListEmptyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.listEmpty.text = "list empty"
        }
    }