package com.example.a.View


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a.APIService
import com.example.a.FacturasVM
import com.example.a.databinding.ActivityListaFacturasBinding
import androidx.activity.viewModels
import co.infinum.retromock.Retromock
import com.example.a.model.Factura
import com.example.a.retromock.ResourceBodyFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListaFacturas : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    var listaFacturasInicial: List<Factura> = ArrayList()
    var listaFacturas: MutableList<Factura> = ArrayList()
    val mAdapter: RecyclerAdapter = RecyclerAdapter()
    val URL_BASE = "https://viewnextandroid3.wiremockapi.cloud/"

    var maxValor : Float = 0f


    private lateinit var binding: ActivityListaFacturasBinding
    val vm :FacturasVM by viewModels{FacturasVM.FacturasVMFactory(application)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaFacturasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        corrutinaFactura()


        binding.btnFiltro.setOnClickListener {
            val intent = Intent(this, FiltroFacturas::class.java)
            maxValor = vm.getImporteMasAlto()
            intent.putExtra("maxValor", maxValor)
            startActivity(intent)
        }

        binding.btnConsumo.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }


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

    private fun ShowError() {
        Toast.makeText(this, "PUES VA A SER QUE NO", Toast.LENGTH_SHORT).show()
    }


    private fun corrutinaFactura(){
        CoroutineScope(Dispatchers.IO).launch {
            val llamada = getRetrofit().create(APIService::class.java).getFacturas()
            val result = llamada.body()
            runOnUiThread{
                if (llamada.isSuccessful){
                    val factura = result?.facturas ?: emptyList()
                    vm.eliminarFacturasDeBD()
                    vm.insertarFacturas(factura)
                    mAdapter.listaFacturas.addAll(factura)
                    mAdapter.notifyDataSetChanged()
                }else{
                    ShowError()
                }
            }
        }
    }



}