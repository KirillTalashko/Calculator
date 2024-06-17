package com.example.calculator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculator.adapter.ListAdapter
import com.example.calculator.adapter.OnClickListener
import com.example.calculator.databinding.FragmentBlankBinding

class BlankFragment() : Fragment() {
    private var _binding: FragmentBlankBinding? = null
    private val binding
        get() = _binding!!

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
    private val listAdapter: ListAdapter by lazy {
        ListAdapter(object : OnClickListener {
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
        val polidrom = "топот"
        val polidromEmpty = ""
        Log.i("Tag", "${isPolidrome(polidrom)}")
        Log.i("Tag", "${isPolidrome(polidromEmpty)}")
        listAdapter.submitList(recyclerList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun isPolidrome(str: String) : Boolean {
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


