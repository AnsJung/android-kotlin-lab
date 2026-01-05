package com.example.roomflow.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.roomflow.database.TextDatabase
import com.example.roomflow.database.entity.TextEntity
import com.example.roomflow.databinding.ActivityMainBinding
import com.example.roomflow.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.text.observe(this) { text ->
            binding.tvResult.text = text
        }
        mainViewModel.collectData()


        binding.btnInsert.setOnClickListener {
           mainViewModel.insertData(
               TextEntity(text=binding.etInput.text.toString())
           )
        }

        binding.btnDelete.setOnClickListener {
           mainViewModel.deleteAll()
        }
    }
}