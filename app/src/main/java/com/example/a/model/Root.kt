package com.example.a.model

import com.example.a.model.Factura
import com.google.gson.annotations.SerializedName

data class Root(
    @SerializedName("numFacturas") val numFacturas: Int,
    @SerializedName("facturas") val facturas: List<Factura>
)