package com.example.calculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.calculator.databinding.SettingActivityBinding

class SettingActivity : Activity() {
    private lateinit var binding: SettingActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.ivBack.setOnClickListener {
            startActivity(Intent(this@SettingActivity, MainActivity::class.java))
        }
    }
}