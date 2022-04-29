package com.kotlin.pruebakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuInflater
import android.view.View.inflate
import android.widget.SearchView
import androidx.activity.viewModels
import com.kotlin.pruebakotlin.databinding.ActivityMainBinding
import com.kotlin.pruebakotlin.databinding.ActivityMainBinding.inflate
import com.kotlin.pruebakotlin.databinding.FragmentDetallleBinding.inflate
import com.kotlin.pruebakotlin.viewmodel.CriptoViewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var search: SearchView


    lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<CriptoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

     viewModel.getData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        if (menu != null) {
            search = menu.findItem(R.id.app_bar_search).actionView as SearchView
            search!!.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
            search!!.setOnQueryTextListener(this)
            return true
        }




        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }


}