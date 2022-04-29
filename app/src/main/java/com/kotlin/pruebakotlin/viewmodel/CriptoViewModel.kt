package com.kotlin.pruebakotlin.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kotlin.pruebakotlin.modelo.Cripto
import com.kotlin.pruebakotlin.modelo.Data
import com.kotlin.pruebakotlin.repository.CriptoMRepository
import com.kotlin.pruebakotlin.repository.CriptoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CriptoViewModel(application: Application) : AndroidViewModel(application) {

    private val criptoRepo = CriptoRepository()
    private val criptoMrepo = CriptoMRepository(getApplication())

    val criptos = criptoMrepo.listar()
    val cripto = MutableLiveData<Data>()


    fun getData()
    {
        CoroutineScope(Dispatchers.IO).launch {
            if(criptoMrepo.getCount() == 0) {
                criptoRepo.getListaCriptos().enqueue(object : Callback<Cripto> {
                    override fun onResponse(call: Call<Cripto>, response: Response<Cripto>) {
                        response.body().let {
                            criptoMrepo.agregar(it!!.data)
                        }
                    }

                    override fun onFailure(call: Call<Cripto>, t: Throwable) {
                        Log.e("CALL", t.message.toString())
                    }
                })
            }
        }

    }

    fun updateCripto(id:String)
    {
        CoroutineScope(Dispatchers.IO).launch {
            cripto.postValue(criptoMrepo.buscar(id))
        }
    }
    fun updatemoney(coin:Data)
    {
        this.cripto.value =coin
    }

}


