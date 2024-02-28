package com.example.a.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a.R
import com.example.a.databinding.ActivitySmartSolarBinding

class SmartSolar : AppCompatActivity() {

    private lateinit var binding: ActivitySmartSolarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySmartSolarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAtras.setOnClickListener({onBackPressedDispatcher.onBackPressed()})
    }
}