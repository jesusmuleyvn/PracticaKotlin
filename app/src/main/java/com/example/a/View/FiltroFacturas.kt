package com.example.a.View

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.a.R
import com.example.a.databinding.ActivityFiltroFacturasBinding
import java.util.Calendar

class FiltroFacturas : AppCompatActivity() {

    private lateinit var binding: ActivityFiltroFacturasBinding

    private var filtrosActivados: MutableList<CheckBox> = ArrayList()


    private var fechasUsadas = 0 // 0 = Ninguna, 1 = Fecha desde, 2 = Fecha hasta, 3 = Ambas
    private val c = Calendar.getInstance()
    private var anyoDesde = Calendar.getInstance()[Calendar.YEAR]
    private var mesDesde = Calendar.getInstance()[Calendar.MONTH]
    private var diaDesde = Calendar.getInstance()[Calendar.DAY_OF_MONTH]


    private var anyoHasta = Calendar.getInstance()[Calendar.YEAR]
    private var mesHasta = Calendar.getInstance()[Calendar.MONTH]
    private var diaHasta = Calendar.getInstance()[Calendar.DAY_OF_MONTH]


    private var cantidadMaxima: Float = 0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiltroFacturasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCerrar.setOnClickListener({onBackPressedDispatcher.onBackPressed()})
        binding.btnDesde.setOnClickListener({mostrarCalendario(binding.btnDesde, anyoDesde, mesDesde, diaDesde, "desde")})
        binding.btnHasta.setOnClickListener({mostrarCalendario(binding.btnHasta, anyoHasta, mesHasta, diaHasta, "hasta")})

        binding.cbFiltroPagadas.setOnClickListener{ _-> actualizarListaFiltros(binding.cbFiltroPagadas) }
        binding.cbFiltroAnuladas.setOnClickListener{ _ -> actualizarListaFiltros(binding.cbFiltroAnuladas) }
        binding.cbFiltroCuotaFija.setOnClickListener { _ -> actualizarListaFiltros(binding.cbFiltroCuotaFija) }
        binding.cbFiltroPendientes.setOnClickListener { _ -> actualizarListaFiltros(binding.cbFiltroPendientes) }
        binding.cbFiltroPlan.setOnClickListener() { _ -> actualizarListaFiltros(binding.cbFiltroPlan) }

        binding.btnDesechar.setOnClickListener { eliminarFiltros() }
        binding.btnAplicar.setOnClickListener { aplicarFiltros() }

        binding.selectorImporte.addOnChangeListener { _, value, _ ->
            binding.tvCantidadSeleccionada.text = String.format("%.2f",  value)
        }

        cantidadMaxima = intent.getFloatExtra("maxValor", 33f)
        binding.selectorImporte.valueTo = cantidadMaxima
        binding.selectorImporte.valueFrom = 0f


    }

    private fun eliminarFiltros() {
        filtrosActivados.forEach { it.isChecked = false }
        filtrosActivados.clear()
        binding.selectorImporte.value = 0f
        fechasUsadas = 0
        binding.btnDesde.text = getString(R.string.dia_mes_anho)
        binding.btnHasta.text = getString(R.string.dia_mes_anho)

    }

    private fun aplicarFiltros() {
        //Toast.makeText(this, "Funcionalidad aún no implementada", Toast.LENGTH_SHORT).show()
        var filtrosString : MutableList<String> = ArrayList()
        filtrosActivados.forEach{filtrosString.add(it.text.toString())}
        var intent : Intent = Intent(this,ListaFacturas::class.java)
        intent.putStringArrayListExtra("filtros", filtrosString as ArrayList<String>)
        startActivity(intent)
    }

    private fun actualizarListaFiltros(boton: CheckBox) {
        if(boton.isChecked){
            filtrosActivados.add(boton)
        }else{
            filtrosActivados.remove(boton)
        }
    }

    private fun mostrarCalendario(boton: Button, anyo: Int, mes: Int, dia: Int, modo: String) {
        val dateDatePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, a, m, d ->
                if(modo == "desde"){
                    this.anyoDesde = a
                    this.mesDesde = m + 1
                    this.diaDesde = d
                    if(this.fechasUsadas == 2 || this.fechasUsadas == 3){
                        this.fechasUsadas = 3
                    }else{
                        this.fechasUsadas = 1
                    }
                }else{
                    this.anyoHasta = a
                    this.mesHasta = m + 1
                    this.diaHasta= a
                    if(this.fechasUsadas == 1 || this.fechasUsadas == 3){
                        this.fechasUsadas = 3
                    }else{
                        this.fechasUsadas = 2
                    }
                }
                boton.text =
                    "" + d + "/" + (m.toInt() + 1).toString() + "/" + a
            },
            anyo,
            mes + 1,
            dia
        )
        dateDatePickerDialog.show()
    }
}