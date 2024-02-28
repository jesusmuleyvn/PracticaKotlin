package com.example.a.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnListaFacturas.setOnClickListener{
            val intent = Intent(this, ListaFacturas::class.java)
            startActivity(intent)
        }

        binding.btnSmartSolar.setOnClickListener{
            val intent = Intent(this, SmartSolar::class.java)
            startActivity(intent)
        }

    }
}