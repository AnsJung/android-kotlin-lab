package com.example.datastroerealex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import com.example.datastroerealex.ui.SettingsKeys
import com.example.datastroerealex.ui.dataStore
import com.example.datastroerealex.ui.theme.DataStroeRealExTheme
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataStroeRealExTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SettingsScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val darkMode by context.dataStore.data
        .map { prefs ->
            prefs[SettingsKeys.DARK_MODE] ?: false
        }
        .collectAsState(initial = false)
    val nickname by context.dataStore.data
        .map { prefs ->
            prefs[SettingsKeys.NICKNAME] ?: ""
        }
        .collectAsState(initial = "")
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Settings Mini", style = MaterialTheme.typography.headlineMedium)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Dark Mode")
            Switch(
                checked = darkMode,
                onCheckedChange = { enabled ->
                    scope.launch {
                        context.dataStore.edit { prefs ->
                            prefs[SettingsKeys.DARK_MODE] = enabled
                        }
                    }
                }
            )
        }

        OutlinedTextField(
            value = nickname,
            onValueChange = { text->
                scope.launch {
                    context.dataStore.edit { prefs ->
                        prefs[SettingsKeys.NICKNAME] = text
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nickname") },
            singleLine = true
        )

        Button(
            onClick = {
                scope.launch {
                    context.dataStore.edit { it.clear() }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset (DataStore)")
        }
        HorizontalDivider()

        Text("Preview", style = MaterialTheme.typography.titleMedium)
        Text("darkMode = $darkMode")
        Text("nickname = $nickname")
    }
}

@Preview
@Composable
fun PreviewSettingsScreen() {
    DataStroeRealExTheme {
        SettingsScreen()
    }
}
