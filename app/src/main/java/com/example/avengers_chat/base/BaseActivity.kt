package com.example.avengers_chat.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.avengers_chat.data.AvengersApi
import com.example.avengers_chat.data.AvengersRepository
import com.example.avengers_chat.ui.ViewModelFactory

abstract class BaseActivity<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : AppCompatActivity() {

    private val api = AvengersApi()
    private val repository = AvengersRepository(api)
    protected val factory = ViewModelFactory(repository)

    private var _binding: VB? = null
    val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        if (_binding == null) {
            throw IllegalArgumentException("Binding cannot be null")
        }
        setContentView(binding.root)
    }

}