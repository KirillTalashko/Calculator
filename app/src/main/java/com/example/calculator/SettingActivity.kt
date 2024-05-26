package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.calculator.databinding.SettingActivityBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: SettingActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).navController
    }
}