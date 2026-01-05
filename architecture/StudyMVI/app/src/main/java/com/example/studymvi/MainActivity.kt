package com.example.studymvi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.studymvi.databinding.ActivityMainBinding
import com.example.studymvi.repository.ImageRepositoryImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MviViewModel by viewModels {
        MviViewModel.MviViewModelFactory(ImageRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding?>(
            this,
            R.layout.activity_main
        ).apply {
            view = this@MainActivity
        }

        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is MVIState.Idle -> binding.progressView.isVisible = false
                    is MVIState.Loading -> binding.progressView.isVisible = true
                    is MVIState.LoadedImage -> {
                        binding.progressView.isVisible = false
                        binding.imageView.run {
                            setBackgroundColor(Color.parseColor(state.image.color))
                            load(state.image.url) {
                                crossfade(300)
                            }
                            binding.imageCountTextView.text = "불러온 이미지 : ${state.count}"
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    fun loadImage() {
        lifecycleScope.launch {
            viewModel.channel.send(MVIIntent.LoadImage)
        }
    }
}