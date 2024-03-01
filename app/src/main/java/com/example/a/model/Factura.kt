package com.example.a.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

@Entity
data class Factura(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("descEstado") val pendientePago: String,
    @SerializedName("importeOrdenacion") val cantidad: Float,
    @SerializedName("fecha") val fecha: String

)
