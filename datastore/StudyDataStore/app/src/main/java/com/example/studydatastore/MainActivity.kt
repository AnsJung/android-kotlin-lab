package com.example.studydatastore

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    // Context의 확장함수로 선언하여 최상위에서 만드는 경우 싱글톤으로 사용 가능하며 by키워드로 lazy하게 함.
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_save).setOnClickListener {
            runBlocking {
                saveData("카리나")
            }
        }

        findViewById<Button>(R.id.btn_get).setOnClickListener {
           getData()
        }

    }

    private fun saveData(value: String) {
        lifecycleScope.launch {
            dataStore.edit { settings ->
                settings[stringPreferencesKey("test")] = value
            }
            Toast.makeText(this@MainActivity, "Saved!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getData() {
        lifecycleScope.launch {
            val preferences = dataStore.data.first()
            val value = preferences[stringPreferencesKey("test")] ?: "No value found"
            Toast.makeText(this@MainActivity, value, Toast.LENGTH_SHORT).show()
        }
    }

}