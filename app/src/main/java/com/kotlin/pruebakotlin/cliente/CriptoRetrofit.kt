package com.kotlin.pruebakotlin.cliente

import com.kotlin.pruebakotlin.servicio.CriptoServicio
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CriptoRetrofit {


    companion object{
        const val BASE_URL = "https://api.coincap.io/"
        private var cliente: Retrofit? = null

        fun getInstancia(url:String) : CriptoServicio
        {
            if(cliente == null)
            {
                cliente = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return cliente!!.create(CriptoServicio::class.java)
        }
    }
}