package com.kotlin.pruebakotlin.servicio

import com.kotlin.pruebakotlin.modelo.Cripto
import retrofit2.Call
import retrofit2.http.GET

interface CriptoServicio {

    @GET("v2/assets")
    fun getCripto() : Call<Cripto>
}