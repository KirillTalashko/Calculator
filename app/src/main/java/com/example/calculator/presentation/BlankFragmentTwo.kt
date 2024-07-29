package com.example.calculator.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.R
import com.example.calculator.databinding.FragmentBlank2Binding
import com.example.calculator.databinding.FragmentBlankBinding
import com.example.calculator.domain.MyRepositoryImpl
import com.example.calculator.model.StatusRequest
import com.example.calculator.presentation.viewModel.MyViewModel
import com.example.calculator.presentation.viewModel.MyViewModelFactory
import com.example.calculator.presentation.viewModel.ViewModelOrder

class BlankFragmentTwo : Fragment() {
    private var _binding: FragmentBlank2Binding? = null
    private val viewModel: MyViewModel by lazy {
        ViewModelProvider(this, factory = MyViewModelFactory(repository = MyRepositoryImpl()))[MyViewModel::class.java]
    }
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlank2Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
        viewModel.stateRequest.observe(viewLifecycleOwner){
            when (it) {
                is StatusRequest.Error ->{
                    binding.progressBar.visibility = View.GONE
                    binding.textRequest.visibility = View.VISIBLE
                    binding.textRequest.text = it.error.toString()
                }
                is StatusRequest.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.textRequest.visibility = View.GONE
                }
                is StatusRequest.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textRequest.visibility = View.VISIBLE
                    binding.textRequest.text = it.data
                }
            }
        }

    }
}