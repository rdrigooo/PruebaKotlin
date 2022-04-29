package com.kotlin.pruebakotlin.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.pruebakotlin.modelo.Cripto
import com.kotlin.pruebakotlin.modelo.Data

@Dao
interface CriptoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun agregar(cripto: List<Data>)

    @Query("select changePercent24Hr, explorer,id,marketCapUsd,maxSupply,name,priceUsd,rank,supply,symbol,volumeUsd24Hr,vwap24Hr from criptos")
    fun listar() : LiveData<List<Data>>

    @Query("select changePercent24Hr,explorer,id,marketCapUsd,maxSupply,name,priceUsd,rank,supply,symbol,volumeUsd24Hr,vwap24Hr from criptos where id = :id")
    fun buscar(id:String) : Data

    @Query("select count(*) from criptos")
    fun contar(): Int
}