package com.example.vuiapp.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vuiapp.data.api.Entity

@Composable
fun DashboardScreen(
    keypass: String,
    viewModel: DashboardViewModel = hiltViewModel(),
    onNavigateToDetails: (Entity) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(keypass) {
        viewModel.getDashboardData(keypass)
    }

    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (val state = uiState) {
                is DashboardUiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize().wrapContentSize()
                )
                is DashboardUiState.Success -> LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(state.entities) { entity ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable { onNavigateToDetails(entity) }
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = "Name: ${entity.name}", style = MaterialTheme.typography.titleMedium)
                                Text(text = "Culture: ${entity.culture}")
                                Text(text = "Domain: ${entity.domain}")
                                Text(text = "Symbol: ${entity.symbol}")
                                Text(text = "Parentage: ${entity.parentage}")
                                Text(text = "Roman Equivalent: ${entity.romanEquivalent}")
                                Text(text = "Description: ${entity.description}", maxLines = 2)
                            }
                        }
                    }
                }
                is DashboardUiState.Error -> Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxSize().wrapContentSize()
                )
            }
        }
    }
}