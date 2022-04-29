package com.kotlin.pruebakotlin.repository

import com.kotlin.pruebakotlin.cliente.CriptoRetrofit
import com.kotlin.pruebakotlin.modelo.Cripto
import retrofit2.Call

class CriptoRepository {

    private val criptowallet = CriptoRetrofit.getInstancia(CriptoRetrofit.BASE_URL)

    fun getListaCriptos(): Call<Cripto> {
        return criptowallet.getCripto()

    }
}