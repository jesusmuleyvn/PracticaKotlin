package com.example.a.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a.APIService
import com.example.a.databinding.ActivityMainBinding
import com.example.a.model.Factura
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    var listaFacturas: MutableList<Factura> = ArrayList()
    val mAdapter: RecyclerAdapter = RecyclerAdapter()
    val URL_BASE = "https://viewnextandroid3.wiremockapi.cloud/"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        buscarFacturas()

        binding.btnFiltro.setOnClickListener({
            val intent = Intent(this, FiltroFacturas::class.java)
            startActivity(intent)
        })

        binding.btnConsumo.setOnClickListener({
            val intent = Intent(this, SmartSolar::class.java)
            startActivity(intent)
        })


    }

    fun getFacturas(): MutableList<Factura>{
        return listaFacturas
    }


    fun setUpRecyclerView(){
        mRecyclerView = binding.listaFacturas
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.RecyclerAdapter(getFacturas())
        mRecyclerView.adapter = mAdapter
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl(URL_BASE).addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun buscarFacturas(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getFacturas()
            val result = call.body()
            runOnUiThread{
                if(call.isSuccessful){
                    val facturas = result?.facturas ?: emptyList()
                    mAdapter.listaFacturas.addAll(facturas)
                    mAdapter.notifyDataSetChanged()
                }else{
                    ShowError()
                }
            }
        }
    }

    private fun ShowError() {
        Toast.makeText(this, "PUES VA A SER QUE NO", Toast.LENGTH_SHORT).show()
    }
}