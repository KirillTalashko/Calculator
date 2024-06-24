package com.example.calculator.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.calculator.R
import com.example.calculator.databinding.FragmentHomeBinding
import com.example.calculator.domain.MyRepositoryImpl
import com.example.calculator.model.Order
import com.example.calculator.model.OrderStatus
import com.example.calculator.presentation.viewModel.MyViewModel
import com.example.calculator.presentation.viewModel.MyViewModelFactory
import com.example.calculator.presentation.viewModel.MyViewModelState


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private var str = StringBuilder()
    private var beforeSign = 0.0
    private var afterSiqn = 0.0
    private var action = ""
    private val repository = MyRepositoryImpl()

    private val viewModel: MyViewModel by lazy {
        ViewModelProvider(this, factory = MyViewModelFactory(repository))[MyViewModel::class.java]
    }
    //private val viewModel: MyViewModel by viewModels { MyViewModelFactory(repository) }

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

        viewModel.resultLiveData.observe(viewLifecycleOwner) {
            binding.inputText.text = it
        }
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is MyViewModelState.Error -> {
                    Log.i("YouTag", it.error.toString())
                }

                MyViewModelState.Loading -> {
                    Log.i("YouTag", it.toString())
                }

                is MyViewModelState.Success -> {
                    Log.i("YouTag", it.data)
                }
            }
        }
        val order = Order(OrderStatus.ORDERED)
        order.printStatus()

        order.proceedToNextStatus()
        order.printStatus()

        order.proceedToNextStatus()
        order.printStatus()

        order.updateStatus(OrderStatus.CANCELLED)
        order.printStatus()

        try {
            order.proceedToNextStatus()
        } catch (e: IllegalStateException) {
            e.message?.let { Log.i("YouTag", it) }
        }

    }

    private fun initView() {
        binding.delete.setOnClickListener {
            str.clear()
            binding.inputText.text = str.toString()
        }
        binding.button1.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string._1))
        }
        binding.button2.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string._2))
        }
        binding.button3.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string._3))
        }
        binding.button4.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string._4))
        }
        binding.button5.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string._5))
        }
        binding.button6.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string._6))
        }
        binding.button7.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string._7))
        }
        binding.button8.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string._8))
        }
        binding.button9.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string._9))
        }
        binding.button0.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string._0))
        }
        binding.plus.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string.plus))
        }
        binding.minus.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string.minus))
        }
        binding.divide.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string.divide))
        }
        binding.multiple.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string.multipli))
        }
        binding.buttonPoint.setOnClickListener {
            binding.inputText.text = str.append(resources.getString(R.string.button_point))
        }
        binding.equally.setOnClickListener {
            viewModel.defineNumbers()
        }
        binding.buttonSettings.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSettingsFragment())
        }

        viewModel.actionLiveData.observe(viewLifecycleOwner) {
            defineAction(it)
        }
    }

    private fun defineAction(str: String) {
        viewModel.action = when (str) {
            resources.getString(R.string.divide) -> Action.DIVIDE
            resources.getString(R.string.multipli) -> Action.MULTIPLI
            resources.getString(R.string.minus) -> Action.MINUS
            resources.getString(R.string.plus) -> Action.PLUS
            else -> Action.DIVIDE
        }
        viewModel.calculate()
    }

    private fun calculate() {
        try {
            val input = str.toString() //преобразуем в string
            val numbers =
                input.split(Regex("[+-/*]")) //разделяем наш массив на разные вводные данные
            if (numbers.size >= 2) {
                beforeSign = numbers[0].toDouble()
                afterSiqn = numbers[1].toDouble()
                action = input[numbers[0].length].toString()
                Log.i("youTag", "a = $beforeSign, b = $afterSiqn, action = $action")
                val result = when (action) {
                    resources.getString(R.string.divide) -> beforeSign / afterSiqn
                    resources.getString(R.string.multipli) -> beforeSign * afterSiqn
                    resources.getString(R.string.minus) -> beforeSign - afterSiqn
                    resources.getString(R.string.plus) -> beforeSign + afterSiqn
                    else -> {
                        throw Exception("Error action")
                    }
                }
                if (result == result.toInt().toDouble()) {
                    binding.inputText.text = result.toInt().toString()
                } else {
                    binding.inputText.text = result.toString()
                }
            } else {
                Log.i("youTag", "Не правильно ввели числа")
            }
        } catch (e: Exception) {
            Log.i("youTag", e.message.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("youTag", "onDestroy")
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(MainActivity.KEY, str.toString())
        Log.i("youTag", "onSaveInstanceState $str ${outState.getString("str")}")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            str.append(savedInstanceState.getString(KEY))
        }
        if (savedInstanceState != null) {
            binding.inputText.text = savedInstanceState.getString(KEY)
        }
    }
}