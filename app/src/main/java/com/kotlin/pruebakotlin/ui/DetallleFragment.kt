package com.kotlin.pruebakotlin.ui

import android.icu.text.DateFormat
import android.icu.text.DateTimePatternGenerator
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.kotlin.pruebakotlin.R
import com.kotlin.pruebakotlin.databinding.FragmentDetallleBinding
import com.kotlin.pruebakotlin.viewmodel.CriptoViewModel
import com.squareup.picasso.Picasso
import java.sql.Date
import java.sql.Time
import java.time.Clock
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.concurrent.timerTask
import kotlin.time.Duration.Companion.hours


class DetallleFragment : Fragment() {
    lateinit var binding: FragmentDetallleBinding
    private val viewModel by activityViewModels<CriptoViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetallleBinding.inflate(layoutInflater)

        viewModel.cripto.observe(viewLifecycleOwner, Observer {
            with(binding){

                Picasso.get().load(getIM(it.symbol)).resize(300,300).into(logoCripto)
                tvId.text = it.id
                tvCambio.text = it.priceUsd.toString()
                tvName.text = it.symbol
                tvSupply.text = it.supply
                val hora:LocalTime = LocalTime.now()
                tvHora.text = hora.toString()
                tvMarket.text = it.marketCapUsd



            }

        })
        return binding.root
    }
    fun getIM(symbol:String) = "https://static.coincap.io/assets/icons/"+symbol.lowercase(
        Locale.getDefault())+"@2x.png"

}


