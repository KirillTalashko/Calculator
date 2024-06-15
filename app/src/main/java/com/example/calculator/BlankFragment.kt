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
        val listempty = emptyList<Int>()
        val list1 = arrayListOf(1, 1, 1, 1)
        val list = arrayListOf(60, 2, 3, 7, 67, 22, 11, 888, 9, 5, 4, 3, 10, 3, 23, 67, 89, 40)
        val liststr = arrayListOf("2", "3", "5", "6")
        try {
            Log.i("Tag", "${findMaxAndSecondList(listempty, true)}")
            Log.i("Tag", "${findMaxAndSecondList(list1, true)}")
            Log.i("Tag", "${findMaxAndSecondList(list, true)}")
            Log.i("Tag", "${findMaxAndSecondList(liststr, false)}")
        }catch (e:IllegalArgumentException){
            e.message
        }
        listAdapter.submitList(recyclerList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun findMaxAndSecondList(list: List<Any>,  selection : Boolean): Int? {
        if (list.size < 2) {
            return null
        } else {
            var max = list[0]
            var second = list[0]
            for (i in list.indices) {
                if (list[i] !is Int) {
                    throw IllegalArgumentException("Wrong argument!")
                }
                if ((max as Int) < (list[i] as Int)) {
                    second = max
                    max = list[i]
                }
            }
            return if (selection) {
                max as Int
            }else second as Int
        }
    }
}


