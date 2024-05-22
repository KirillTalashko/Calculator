package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val str = StringBuilder()
    private var beforeSign = ""
    private var afterSiqn = ""
    private var action = ""
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val KEY = "str"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Log.i("youTag", "onCreate ${savedInstanceState?.getString("str")}")
        setContentView(binding.root)
        initView()
    }

    private fun initView(){
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
        binding.button0.setOnClickListener{
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
        binding.buttonPoint.setOnClickListener{
            binding.inputText.text = str.append(resources.getString(R.string.button_point))
        }
        binding.equally.setOnClickListener {
            calculate()
        }
        binding.buttonSettings.setOnClickListener {
            val intent = Intent(this@MainActivity, SettingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun calculate() {
        try {
            val input = str.toString() //преобразуем в string
            val numbers =
                input.split(Regex("[+-/*]")) //разделяем наш массив на разные вводные данные
            if (numbers.size >= 2) {
                beforeSign = numbers[0]
                afterSiqn = numbers[1]
                action = input[numbers[0].length].toString()
                Log.i("youTag", "a = $beforeSign, b = $afterSiqn, action = $action")
                val result = when (action) {
                    resources.getString(R.string.divide) -> beforeSign.toDouble() / afterSiqn.toDouble()
                    resources.getString(R.string.multipli) -> beforeSign.toDouble() * afterSiqn.toDouble()
                    resources.getString(R.string.minus) -> beforeSign.toDouble() - afterSiqn.toDouble()
                    resources.getString(R.string.plus) -> beforeSign.toDouble() + afterSiqn.toDouble()
                    else -> {
                        throw Exception("Error action")
                    }
                }
                binding.inputText.text = result.toString()
            } else {
                Log.i("youTag", "Не правильно ввели числа")
            }
        } catch (e: Exception) {
            Log.i("youTag", e.message.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("youTag", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("youTag", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("youTag", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("youTag", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("youTag", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("youTag", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY, str.toString())
        Log.i("youTag", "onSaveInstanceState $str ${outState.getString("str")}")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("youTag", "onRestoreInstanceState ${savedInstanceState.getString("str")}")
        str.append(savedInstanceState.getString(KEY))
        binding.inputText.text = savedInstanceState.getString(KEY)
    }
}
