package com.kotlin.pruebakotlin.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kotlin.pruebakotlin.dao.CriptoDao
import com.kotlin.pruebakotlin.modelo.Cripto
import com.kotlin.pruebakotlin.modelo.Data

@Database(entities = [Data::class], version = 1)
abstract class CriptoDatabase : RoomDatabase() {

    abstract fun criptoDao() : CriptoDao

    companion object{
        @Volatile
        private var instancia:CriptoDatabase? = null

        fun getInstancia(context: Context):CriptoDatabase {

            if(instancia == null ){
                synchronized(this)
                {
                    instancia = Room.databaseBuilder(context,
                        CriptoDatabase::class.java,
                        "cripto_wallet")
                        .build()
                }
            }
            return instancia!!
        }

    }

}