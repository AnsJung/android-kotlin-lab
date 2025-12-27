package com.example.datastroerealex.ui

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object SettingsKeys {
    val DARK_MODE = booleanPreferencesKey("dark_mode")
    val NICKNAME = stringPreferencesKey("nickname")
}