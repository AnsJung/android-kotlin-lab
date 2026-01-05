package com.example.datastroerealex

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.datastroerealex.ui.SettingsKeys
import com.example.datastroerealex.ui.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsStore(
    private val appContext: Context
) {
    val darkModeFlow: Flow<Boolean> =
        appContext.dataStore.data.map { prefs ->
            prefs[SettingsKeys.DARK_MODE] ?: false
        }
    val nicknameFlow: Flow<String> =
        appContext.dataStore.data.map { prefs ->
            prefs[SettingsKeys.NICKNAME] ?: ""
        }

    suspend fun setDarkMode(enabled: Boolean) {
        appContext.dataStore.edit { prefs ->
            prefs[SettingsKeys.DARK_MODE] = enabled
        }
    }

    suspend fun setNickname(name: String) {
        appContext.dataStore.edit { prefs ->
            prefs[SettingsKeys.NICKNAME] = name
        }
    }

    suspend fun clearAll() {
        appContext.dataStore.edit { it.clear() }
    }
}