package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculator.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {
    private var _binding: FragmentBlankBinding? = null
    private val adapter by lazy {
        Adapter(
            recyclerList
        )
    }
    private val binding
        get() = _binding!!

    private val recyclerList = listOf(
        "Element_1",
        "Element_2",
        "Element_3",
        "Element_4",
        "Element_5",
        "Element_6",
        "Element_7",
        "Element_8",
        "Element_9",
        "Element_10"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlankBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.apply {
            rcView.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}