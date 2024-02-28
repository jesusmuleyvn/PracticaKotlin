package com.example.a

import com.example.a.model.Root
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("facturas")
    suspend fun getFacturas(): Response<Root>
}