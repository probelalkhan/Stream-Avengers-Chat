package com.example.avengers_chat.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.avengers_chat.base.BaseActivity
import com.example.avengers_chat.data.Resource
import com.example.avengers_chat.databinding.ActivityMainBinding
import com.example.avengers_chat.ui.custom.StreamGlobalStyles
import com.example.avengers_chat.extensions.adapterPositionOrNull
import com.example.avengers_chat.extensions.bindStatusBarColor
import com.example.avengers_chat.extensions.parsedColor
import com.example.avengers_chat.extensions.startCircularReveal
import com.yarolegovich.discretescrollview.transform.ScaleTransformer

class MainActivity
    : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private lateinit var viewModel: MainActivityViewModel
    private val adapter = AvengersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRecyclerView()

        viewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
        viewModel.avengers.observe(this) { result ->
            when (result) {
                is Resource.Failure -> {
                }
                Resource.Loading -> {
                }
                is Resource.Success -> {
                    val avengers = result.data
                    adapter.submitList(avengers)
                }
            }
        }
    }

    private fun setupRecyclerView() = with(binding) {
        recyclerView.adapter = adapter

        recyclerView.addOnItemChangedListener { viewHolder, _ ->
            val position = viewHolder?.adapterPositionOrNull ?: return@addOnItemChangedListener
            if (position >= 0 && position < adapter.itemCount) {
                val parsedColor = adapter.getListItem(position).parsedColor
                recyclerView.bindStatusBarColor(parsedColor)
                pointView.startCircularReveal(parsedColor)
                StreamGlobalStyles.updatePrimaryColorGlobalStyles(parsedColor)
            }
        }

        recyclerView.setItemTransformer(
            ScaleTransformer.Builder()
                .setMaxScale(1.20f)
                .setMinScale(0.8f)
                .build()
        )

        adapter.itemClickListener = { item, position, view ->
            AvengersChannelListActivity.startActivity(this@MainActivity, item)
        }
    }
}