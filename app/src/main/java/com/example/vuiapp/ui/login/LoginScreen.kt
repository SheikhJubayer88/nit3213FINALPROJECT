package com.example.vuiapp.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToDashboard: (String) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    // Track if navigation already happened
    var hasNavigated by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 32.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.login(username, password) }, modifier = Modifier.fillMaxWidth()) {
            Text("Login")
        }

        // Handle UI state properly
        when (val state = uiState) {
            is LoginUiState.Loading -> CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            is LoginUiState.Success -> {
                // Navigate to Dashboard only once
                LaunchedEffect(Unit) {
                    if (!hasNavigated) {
                        hasNavigated = true
                        onNavigateToDashboard(state.keypass)
                    }
                }
            }
            is LoginUiState.Error -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            else -> {} // Handle Idle state
        }
    }
}