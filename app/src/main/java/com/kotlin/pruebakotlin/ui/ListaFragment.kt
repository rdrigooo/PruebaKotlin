package com.kotlin.pruebakotlin.ui

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.pruebakotlin.R
import com.kotlin.pruebakotlin.adapter.CriptoAdapter
import com.kotlin.pruebakotlin.databinding.FragmentListaBinding
import com.kotlin.pruebakotlin.modelo.Data
import com.kotlin.pruebakotlin.viewmodel.CriptoViewModel


class ListaFragment : Fragment() {

    lateinit var binding: FragmentListaBinding
    private val viewModel by activityViewModels<CriptoViewModel>()
    private val adapter = CriptoAdapter()
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

                with(binding){
                    tvUser.setOnEditorActionListener(OnEditorActionListener { textView, actionId, keyEvent ->
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == KeyEvent.ACTION_DOWN || actionId == KeyEvent.KEYCODE_ENTER) {
                        val user =  tvUser.text.toString()
                        sharedPreferences = requireActivity().getSharedPreferences(user,MODE_PRIVATE)
                        Toast.makeText(requireContext(), getString(R.string.mensajeuser),Toast.LENGTH_LONG).show()
                        sharedPreferences.edit().putString(user,user).commit()
                        tvUser.isEnabled = false

                        return@OnEditorActionListener true
                    }
                    false
                })

                }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListaBinding.inflate(layoutInflater)
        val layoutManager = LinearLayoutManager(requireContext())
        with(binding) {
            rvCripto.adapter = adapter
            rvCripto.layoutManager = layoutManager

        }

        adapter.setMilistener(object : CriptoAdapter.MiListener {
            override fun miOnclick(data: Data) {
                viewModel.updateCripto(data.id)
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_listaFragment_to_detallleFragment)
            }
        })
        viewModel.criptos.observe(viewLifecycleOwner, Observer {
            adapter.updateData(it)
        })
        return binding.root
    }

}