package com.kotlin.pruebakotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.pruebakotlin.R
import com.kotlin.pruebakotlin.databinding.ItemLayoutBinding
import com.kotlin.pruebakotlin.modelo.Data
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class CriptoAdapter: RecyclerView.Adapter<CriptoAdapter.CustomViewHolder>(){
    class CustomViewHolder (itemView: View, val listener: MiListener): RecyclerView.ViewHolder(itemView){

        private val binding = ItemLayoutBinding.bind(itemView)

        fun bindData(criptos: Data){
            with(binding){
                tvNOmbreCripto.text = criptos.name
                tvUsdRV.text = criptos.priceUsd
                Picasso.get().load(getIM(criptos.symbol)).resize(100,100).into(imCriptoLogo)
                itemView.setOnClickListener {
                    listener.miOnclick(criptos)
                }

            }



        }
        fun getIM(symbol:String) = "https://static.coincap.io/assets/icons/"+symbol.lowercase(
            Locale.getDefault())+"@2x.png"

    }

    var lista: List<Data> = ArrayList()
    lateinit var listener: MiListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return CustomViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindData(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun updateData(data: List<Data>)
    {
        lista = data
        notifyDataSetChanged()

    }

    interface MiListener{
        fun miOnclick(data:Data)
    }

    fun setMilistener(listener:MiListener)
    {
        this.listener=listener
    }

}