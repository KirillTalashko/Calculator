package com.example.calculator.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.calculator.presentation.viewModel.MyViewModel
import com.example.calculator.R
import com.example.calculator.databinding.FragmentHomeBinding
import com.example.calculator.domain.MyRepositoryImpl
import com.example.calculator.extentions.log
import com.example.calculator.model.StatusRequest
import com.example.calculator.presentation.viewModel.MyViewModelFactory


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel by lazy {ViewModelProvider(this, factory = MyViewModelFactory(repository = MyRepositoryImpl()))[MyViewModel::class.java]}

    companion object {
        const val KEY = "str"
    }

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getData()
        viewModel.resultViewModel.observe(viewLifecycleOwner) { str ->
            binding.inputText.text = str
        }
    }

    private fun initView() {
        binding.delete.setOnClickListener {
            viewModel.str.clear()
            binding.inputText.text = viewModel.str.toString()
        }
        binding.button1.setOnClickListener {
            binding.inputText.text = viewModel.str.append(resources.getString(R.string._1))
        }
        binding.button2.setOnClickListener {
            binding.inputText.text = viewModel.str.append(resources.getString(R.string._2))
        }
        binding.button3.setOnClickListener {
            binding.inputText.text = viewModel.str.append(resources.getString(R.string._3))
        }
        binding.button4.setOnClickListener {
            binding.inputText.text = viewModel.str.append(resources.getString(R.string._4))
        }
        binding.button5.setOnClickListener {
            binding.inputText.text = viewModel.str.append(resources.getString(R.string._5))
        }
        binding.button6.setOnClickListener {
            binding.inputText.text = viewModel.str.append(resources.getString(R.string._6))
        }
        binding.button7.setOnClickListener {
            binding.inputText.text = viewModel.str.append(resources.getString(R.string._7))
        }
        binding.button8.setOnClickListener {
            binding.inputText.text = viewModel.str.append(resources.getString(R.string._8))
        }
        binding.button9.setOnClickListener {
            binding.inputText.text = viewModel.str.append(resources.getString(R.string._9))
        }
        binding.button0.setOnClickListener {
            binding.inputText.text = viewModel.str.append(resources.getString(R.string._0))
        }
        binding.plus.setOnClickListener {
            binding.inputText.text = viewModel.str.append(resources.getString(R.string.plus))
        }
        binding.minus.setOnClickListener {
            binding.inputText.text = viewModel.str.append(resources.getString(R.string.minus))
        }
        binding.divide.setOnClickListener {
            binding.inputText.text = viewModel.str.append(resources.getString(R.string.divide))
        }
        binding.multiple.setOnClickListener {
            binding.inputText.text = viewModel.str.append(resources.getString(R.string.multipli))
        }
        binding.buttonPoint.setOnClickListener {
            binding.inputText.text =
                viewModel.str.append(resources.getString(R.string.button_point))
        }
        binding.equally.setOnClickListener {
            viewModel.defineNumber()
        }
        binding.buttonSettings.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSettingsFragment())
        }
        viewModel.actionList.observe(viewLifecycleOwner) {
            defineAction(it)
        }
    }

    private fun defineAction(str: String) {
        viewModel.action = when (str) {
            resources.getString(R.string.divide) -> Action.DIVIDE
            resources.getString(R.string.multipli) -> Action.MULTIPLI
            resources.getString(R.string.plus) -> Action.PLUS
            resources.getString(R.string.minus) -> Action.MINUS
            else -> {
                Action.DIVIDE
            }
        }
        viewModel.calculate()
    }

    override fun onDestroy() {
        super.onDestroy()
        "onDestroy".log()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(MainActivity.KEY, viewModel.str.toString())
        "onSaveInstanceState ${viewModel.str} ${outState.getString("str")}".log()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            binding.inputText.text = savedInstanceState.getString(KEY)
        }
    }
}
