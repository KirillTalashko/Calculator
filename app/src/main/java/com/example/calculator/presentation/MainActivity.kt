package com.example.calculator.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import com.example.calculator.R
import com.example.calculator.utils.Settings

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY = "str"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        when(Settings.loadTheme(this)){
            2 -> {
                setTheme(R.style.AppThemeDark)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                setTheme(R.style.AppThemeLight)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (supportFragmentManager.findFragmentById(R.id.container_1) as NavHostFragment).navController
    }

    override fun onStart() {
        super.onStart()
        Log.i("youTag", "onStart")
        val cat1 = Cat("fd", 1)
        val cat2 = Cat("fd", 1)
        val eq = cat1 === cat2
        Log.i("youTag", "$eq")
    }

    data class Cat(val name: String, val age: Int)

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
}
