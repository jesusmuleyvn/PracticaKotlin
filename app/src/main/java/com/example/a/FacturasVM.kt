package com.example.a

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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
    init {
        facturaDao = database.facturaDao()
    }
    fun insertFacturasFromApi(facturas: List<Factura>) {
        viewModelScope.launch {
            facturaDao.insertFacturas(facturas)
        }
    }

    fun getImporteMasAlto() : Float{
        var importeMax : Float = 0f
        viewModelScope.launch {
            importeMax = facturaDao.getMayorImporte()
        }
        return importeMax
    }

    class FacturasVMFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun<T : ViewModel> create(modelClass:Class<T>): T {
            return FacturasVM(application) as T
        }
    }
}