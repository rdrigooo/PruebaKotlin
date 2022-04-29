package com.kotlin.pruebakotlin.modelo


import com.google.gson.annotations.SerializedName

data class Cripto(
    @SerializedName("data")
    val `data`: List<Data>,

)