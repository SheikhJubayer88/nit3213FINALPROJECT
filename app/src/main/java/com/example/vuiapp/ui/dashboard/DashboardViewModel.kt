package com.example.vuiapp.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vuiapp.data.api.Entity
import com.example.vuiapp.data.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class DashboardUiState {
    object Loading : DashboardUiState()
    data class Success(val entities: List<Entity>) : DashboardUiState()
    data class Error(val message: String) : DashboardUiState()
}

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState

    fun getDashboardData(keypass: String) {
        viewModelScope.launch {
            _uiState.value = DashboardUiState.Loading
            try {
                val response = repository.getDashboard(keypass)
                if (response.isSuccessful) {
                    val dashboardResponse = response.body()
                    if (dashboardResponse != null) {
                        _uiState.value = DashboardUiState.Success(dashboardResponse.entities)
                    } else {
                        _uiState.value = DashboardUiState.Error("Empty response from server.")
                    }
                } else {
                    _uiState.value = DashboardUiState.Error("Failed to fetch dashboard data.")
                }
            } catch (e: Exception) {
                _uiState.value = DashboardUiState.Error(e.localizedMessage ?: "Unknown error occurred.")
            }
        }
    }
}