package com.example.calculator.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.databinding.FragmentBlankBinding
import com.example.calculator.domain.MyRepositoryImpl
import com.example.calculator.presentation.adapter.ListAdapter
import com.example.calculator.presentation.adapter.OnClickListener
import com.example.calculator.presentation.viewModel.MyViewModel
import com.example.calculator.presentation.viewModel.MyViewModelFactory
import com.example.calculator.presentation.viewModel.MyViewModelState

class BlankFragment : Fragment(), OnClickListener {
    private var _binding: FragmentBlankBinding? = null
    private val binding
        get() = _binding!!

    //val viewModel: MyViewModel by viewModels<MyViewModel>() { MyRepositoryImpl() }
    private val viewModel =
        ViewModelProvider(this, MyViewModelFactory(MyRepositoryImpl()))[MyViewModel::class.java]

    private val recyclerList = mutableListOf(
        "News_1",
        "News_2",
        "News_3",
        "News_4",
        "News_5",
        "News_6",
        "News_7",
        "News_8",
    )

    override fun onClickDelete(position: Int) {
        val currentList = listAdapter.currentList.toMutableList()
        currentList.removeAt(position)
        listAdapter.submitList(currentList)
    }

    override fun onClickAdd() {
        Log.i("Tag", "${recyclerList.size}")
        val currentList = listAdapter.currentList.toMutableList()
        currentList.add("News_${currentList.size + 1}")
        listAdapter.submitList(currentList)
    }

    private val listAdapter: ListAdapter by lazy {
        ListAdapter(this)
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
        val polidrom = "топот"
        val polidromEmpty = ""
        Log.i("Tag", "${isPolidrome(polidrom)}")
        Log.i("Tag", "${isPolidrome(polidromEmpty)}")
        listAdapter.submitList(recyclerList)
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is MyViewModelState.Error -> TODO()
                MyViewModelState.Loading -> TODO()
                is MyViewModelState.Success -> TODO()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun isPolidrome(str: String): Boolean {
        if (str.isEmpty()) {
            return false
        } else {
            var left = 0
            var right = str.length - 1
            while (str[left] < str[right]) {
                if (str[left] != str[right]) {
                    return false
                }
                left++
                right--
            }
        }
        return true
    }
}


