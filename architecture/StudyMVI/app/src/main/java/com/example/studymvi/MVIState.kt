package com.example.studymvi

import com.example.studymvi.model.Image

sealed class MVIState {
    data object Idle : MVIState()

    data object Loading : MVIState()

    data class LoadedImage(val image: Image, val count: Int) : MVIState()
}