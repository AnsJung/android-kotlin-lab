package com.example.listadapter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.listadapter.viewmodel.MainViewModel
import com.example.listadapter.R
import com.example.listadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = mainViewModel
        binding.lifecycleOwner = this
        Handler(Looper.getMainLooper()).postDelayed({
            mainViewModel.changeList()
        }, 5000)
    }
}