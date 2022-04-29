package com.kotlin.pruebakotlin.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.kotlin.pruebakotlin.modelo.Cripto
import com.kotlin.pruebakotlin.modelo.Data
import com.kotlin.pruebakotlin.room.CriptoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CriptoMRepository (var context: Context) {

    private val db = CriptoDatabase.getInstancia(context)

    fun agregar(cripto:List<Data>) {
        CoroutineScope(Dispatchers.IO).launch {
            db.criptoDao().agregar(cripto)
        }
    }

    fun listar(): LiveData<List<Data>>{
        return  db.criptoDao().listar()
    }

    fun buscar(id:String):Data{
        return db.criptoDao().buscar(id)
    }

    fun getCount():Int{
        return db.criptoDao().contar()

    }


}