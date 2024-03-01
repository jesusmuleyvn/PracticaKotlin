package com.example.a.View

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.example.a.model.Factura
import com.example.a.retromock.ResourceBodyFactory
import retrofit2.Call
import retrofit2.http.GET

interface RetromockService {
    @Mock
    @MockResponse(body = "Facturas.json")
    fun getFacturas() : Call<Factura>
}