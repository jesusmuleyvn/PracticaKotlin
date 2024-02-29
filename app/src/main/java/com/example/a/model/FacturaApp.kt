package com.example.a.model

import android.app.Application
import androidx.room.Room

class FacturaApp : Application() {

    val room = Room.databaseBuilder(this, FacturaDB::class.java, "factura").build()
}