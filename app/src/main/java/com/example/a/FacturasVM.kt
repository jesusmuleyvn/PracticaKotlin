package com.example.a

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.a.model.Factura
import com.example.a.model.FacturaDB
import com.example.a.model.FacturaDao
import kotlinx.coroutines.launch

class FacturasVM(application: Application) : AndroidViewModel(application) {
    private val facturaDao: FacturaDao
    private val database: FacturaDB by lazy{FacturaDB.getDatabase(application)}


    private var importeMax : Float = 100f
    private lateinit var listaFiltrada : List<Factura>
    init {
        facturaDao = database.facturaDao()
    }




    fun insertarFacturas(facturas: List<Factura>) {
        viewModelScope.launch {
            facturaDao.insertFacturas(facturas)
        }
    }

    fun getFacturasPorFiltro(filtros: List<String>) : List<Factura>{
        viewModelScope.launch {
            with(this){
                listaFiltrada = facturaDao.filtrarFacturas(filtros)
            }
        }
        return listaFiltrada
    }

    fun eliminarFacturasDeBD(){
        viewModelScope.launch {
            facturaDao.eliminarContenidoBaseDeDatos()
        }
    }


    fun getImporteMasAlto() : Float{
        viewModelScope.launch {
            with(this){
                importeMax = facturaDao.getMayorImporte()
            }
        }
        return importeMax
    }

    class FacturasVMFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun<T : ViewModel> create(modelClass:Class<T>): T {
            return FacturasVM(application) as T
        }
    }
}