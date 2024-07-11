package com.example.calculator.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.presentation.adapter.ListAdapter
import com.example.calculator.presentation.adapter.OnClickListener
import com.example.calculator.databinding.FragmentBlankBinding
import com.example.calculator.domain.MyRepositoryImpl
import com.example.calculator.model.Order
import com.example.calculator.model.OrderStatus
import com.example.calculator.presentation.viewModel.MyViewModelFactory
import com.example.calculator.presentation.viewModel.ViewModelOrder

class BlankFragment() : Fragment() {
    private var _binding: FragmentBlankBinding? = null
    private val viewModel: ViewModelOrder by lazy {
        ViewModelProvider(this, factory = MyViewModelFactory(repository = MyRepositoryImpl()))[ViewModelOrder::class.java]
    }
    private val binding
        get() = _binding!!


    private val listAdapter: ListAdapter by lazy {
        ListAdapter(object : OnClickListener {
            override fun onClickDelete(position: Int) {
                viewModel.deleteOrder(position)
            }

            override fun onClickAdd(position: Int) {
                viewModel.addOrder(Order("Заказ_${position + 1}", OrderStatus.ORDERED))
            }

            override fun onClickLoading(item: Order, adapterPosition: Int) {
                viewModel.loadingOrder(item,adapterPosition)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlankBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcView.adapter = listAdapter
        viewModel.getOrder()
        viewModel.order.observe(viewLifecycleOwner){
            listAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


