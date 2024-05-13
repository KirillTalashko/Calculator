package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val str = StringBuilder()
    private var beforeSign = ""
    private var afterSiqn = ""
    private var action = ""
    companion object {
        const val KEY = "str"
    }

    private val inputText by lazy { findViewById<TextView>(R.id.input_text) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.button_1)
        val button2 = findViewById<Button>(R.id.button_2)
        val button3 = findViewById<Button>(R.id.button_3)
        val button4 = findViewById<Button>(R.id.button_4)
        val button5 = findViewById<Button>(R.id.button_5)
        val button6 = findViewById<Button>(R.id.button_6)
        val button7 = findViewById<Button>(R.id.button_7)
        val button8 = findViewById<Button>(R.id.button_8)
        val button9 = findViewById<Button>(R.id.button_9)
        val plus = findViewById<Button>(R.id.plus)
        val minus = findViewById<Button>(R.id.minus)
        val multiple = findViewById<Button>(R.id.multiple)
        val divide = findViewById<Button>(R.id.divide)
        val equally = findViewById<Button>(R.id.equally)
        val delete = findViewById<Button>(R.id.delete)
        val buttonPoint = findViewById<Button>(R.id.button)

        button1.setOnClickListener {
            inputText.text = str.append("1")
        }
        button2.setOnClickListener {
            inputText.text = str.append("2")
        }
        button3.setOnClickListener {
            inputText.text = str.append("3")
        }
        button4.setOnClickListener {
            inputText.text = str.append("4")
        }
        button5.setOnClickListener {
            inputText.text = str.append("5")
        }
        button6.setOnClickListener {
            inputText.text = str.append("6")
        }
        button7.setOnClickListener {
            inputText.text = str.append("7")
        }
        button8.setOnClickListener {
            inputText.text = str.append("8")
        }
        button9.setOnClickListener {
            inputText.text = str.append("9")
        }
        plus.setOnClickListener {
            inputText.text = str.append("+")
        }
        minus.setOnClickListener {
            inputText.text = str.append("-")
        }
        divide.setOnClickListener {
            inputText.text = str.append("/")
        }
        multiple.setOnClickListener {
            inputText.text = str.append("*")
        }
        buttonPoint.setOnClickListener {
            inputText.text = str.append("")
        }
        equally.setOnClickListener {
            calculate()
        }
        delete.setOnClickListener {
            str.clear()
            inputText.text = str.toString()
        }
    }

    private fun calculate() {
        try {
            val input = str.toString() //преобразуем в string
            val numbers = input.split(Regex("[+-/*]")) //разделяем наш массив на разные вводные данные
            if (numbers.size >= 2) {
                beforeSign = numbers[0]
                afterSiqn = numbers[1]
                action = input[numbers[0].length].toString()
                Log.i("youTag", "a = $beforeSign, b = $afterSiqn, action = $action")
                val result = when (action) {
                    resources.getString(R.string.divide)-> beforeSign.toDouble() / afterSiqn.toDouble()
                    resources.getString(R.string.multipli) -> beforeSign.toDouble() * afterSiqn.toDouble()
                    resources.getString(R.string.minus) -> beforeSign.toDouble() - afterSiqn.toDouble()
                    resources.getString(R.string.plus) -> beforeSign.toDouble() + afterSiqn.toDouble()
                    else -> {
                        throw Exception("Error action")
                    }
                }
                inputText.text = result.toString()
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
        inputText.text = savedInstanceState.getString(KEY)
    }
}
