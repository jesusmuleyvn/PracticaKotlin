package com.example.a.View

import android.app.Dialog
import android.os.Build
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.a.R
import com.example.a.databinding.RecyclerViewItemBinding
import com.example.a.model.Factura
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var binding = RecyclerViewItemBinding.bind(view)
    val euro = itemView.context.getString(R.string.euro)

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(factura: Factura){
        val fechaDelimitada = factura.fecha.split("/")
        val fechaCambiada = LocalDate.parse(fechaDelimitada.get(2) + "-" + fechaDelimitada.get(1) + "-" + fechaDelimitada.get(0)
                            , DateTimeFormatter.ISO_DATE)
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale("es", "ES"))
        var fechaFormateada = fechaCambiada.format(formatter)

        binding.Fecha.text = fechaFormateada.toString().uppercase()
        binding.montoFactura.text = factura.cantidad.toString() + euro
        if(factura.pendientePago == "Pagada") {
            binding.PendientePago.visibility = View.GONE
            binding.Fecha.gravity = Gravity.CENTER_VERTICAL
        }
        binding.PendientePago.text = factura.pendientePago

        itemView.setOnClickListener{
            mostrarPopup()
        }
    }

    private fun mostrarPopup() {
        val dialog = Dialog(itemView.context)
        dialog.setContentView(R.layout.popup_view)
        dialog.findViewById<TextView>(R.id.btnCerrarPopup).setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }
}