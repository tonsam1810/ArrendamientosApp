package pe.cibertec.dam.arrendamientosapp.proxy.interfaces

import pe.cibertec.dam.arrendamientosapp.model.Pagos
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface PagoService {
    @GET("pagos")
    suspend fun getPagos(): Response<List<Pagos>>

    @GET("pagos/{id}")
    // http://10.0.2.2:8080/docente/v1/docente/1
    suspend fun getPago(@Path("id") id_pagos: Int): Response<Pagos>


    @POST("pagos")
    suspend fun savePagos(@Body pagos: Pagos): Response<Pagos>




}