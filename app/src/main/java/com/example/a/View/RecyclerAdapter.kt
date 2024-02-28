package com.example.a.View

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.a.R
import com.example.a.model.Factura

class RecyclerAdapter : RecyclerView.Adapter<ViewHolder>() {
    var listaFacturas: MutableList<Factura> = ArrayList()

    fun RecyclerAdapter(listaFacturas: MutableList<Factura>){
        this.listaFacturas = listaFacturas
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaFacturas[position]
        holder.bind(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.recycler_view_item, parent, false))
    }
    override fun getItemCount(): Int {
        return listaFacturas.size
    }

}