package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.calculator.R.layout.fragment_home
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.extentions.log

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY = "str"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        when (Settings.loadTheme(this)) {
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
        "onStart".log()
    }

    override fun onResume() {
        super.onResume()
        "onResume".log()
    }

    override fun onRestart() {
        super.onRestart()
        "onRestart".log()
    }

    override fun onPause() {
        super.onPause()
        "onPause".log()
    }

    override fun onStop() {
        super.onStop()
        "onStop".log()
    }

    override fun onDestroy() {
        super.onDestroy()
        "onDestroy".log()
    }
}
