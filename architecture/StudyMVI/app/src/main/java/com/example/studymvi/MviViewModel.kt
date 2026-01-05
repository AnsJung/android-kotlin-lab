package com.example.studymvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.studymvi.repository.ImageRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MviViewModel(private val imageRepository: ImageRepository) : ViewModel() {

    val channel = Channel<MVIIntent>()
    private val _state = MutableStateFlow<MVIState>(MVIState.Idle)
    val state: StateFlow<MVIState> get() = _state

    private var count = 0;

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collectLatest {
                when (it) {
                    MVIIntent.LoadImage -> {
                        loadImage()
                    }
                }
            }
        }
    }

    private fun loadImage() {
        viewModelScope.launch {
            _state.value = MVIState.Loading
            val image = imageRepository.getRandomImage()
            count++
            _state.value = MVIState.LoadedImage(image, count)
        }
    }

    class MviViewModelFactory(private val imageRepository: ImageRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MviViewModel(imageRepository) as T
        }
    }
}