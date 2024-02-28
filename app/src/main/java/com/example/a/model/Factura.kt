package com.example.a.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Factura(
    @SerializedName("descEstado") val pendientePago: String,
    @SerializedName("importeOrdenacion") val cantidad: Double,
    @SerializedName("fecha") val fecha: String
)
