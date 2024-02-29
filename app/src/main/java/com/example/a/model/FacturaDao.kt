package com.example.a.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FacturaDao {

    @Query("Select * from Factura")
    suspend fun getAll(): List<Factura>


    @Query("SELECT * FROM Factura")
    fun getAllFacturas(): LiveData<List<Factura>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFacturas(facturas: List<Factura>)
    @Query("DELETE FROM Factura")
    suspend fun eliminarBaseDeDatos()


    @Query("Select MAX(cantidad) FROM Factura")
    suspend fun getMayorImporte(): Float

}