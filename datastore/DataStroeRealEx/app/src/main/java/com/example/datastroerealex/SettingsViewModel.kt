package com.example.datastroerealex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val store: SettingsStore
) : ViewModel() {
    val uiState: StateFlow<SettingsUiState> =
        combine(store.darkModeFlow, store.nicknameFlow) { dark, name ->
            SettingsUiState(darkMode = dark, nickname = name)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SettingsUiState()
        )


    fun onDarkModeChanged(enabled: Boolean) {
        viewModelScope.launch {
            store.setDarkMode(enabled)
        }
    }

    fun onNicknameChanged(name: String) {
        viewModelScope.launch {
            store.setNickname(name)
        }
    }

    fun onResetClicked() {
        viewModelScope.launch {
            store.clearAll()
        }
    }
}

