package com.example.calculator.presentation.adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.databinding.ItemListBinding
import com.example.calculator.databinding.ItemListEmptyBinding
import com.example.calculator.model.Order


class MyViewHolder(
    private val binding: ItemListBinding,
    private val clickListener: OnClickListener
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Order, position : Int) {
        binding.textItem.text = item.description
        binding.textLoading.text = item.ordered.description
        binding.imageDelete.setOnClickListener {
            clickListener.onClickDelete(adapterPosition)
        }
        binding.imageAdd.setOnClickListener {
            clickListener.onClickAdd(position)
        }
        binding.imageLoading.setOnClickListener {
            clickListener.onClickLoading(item, adapterPosition)
        }
    }
}
    class MyViewHolderEmpty(private val binding: ItemListEmptyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.listEmpty.text = "list empty"
        }
    }