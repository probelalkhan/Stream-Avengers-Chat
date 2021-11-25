package com.example.avengers_chat

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.avengers_chat.base.BaseActivity
import com.example.avengers_chat.data.Resource
import com.example.avengers_chat.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private val adapter = AvengersAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
        viewModel.avengers.observe(this){ result ->
            when(result){
                is Resource.Failure -> {}
                Resource.Loading -> {}
                is Resource.Success -> {
                    val avengers = result.data
                    adapter.submitList(avengers)
                }
            }
        }
    }
}