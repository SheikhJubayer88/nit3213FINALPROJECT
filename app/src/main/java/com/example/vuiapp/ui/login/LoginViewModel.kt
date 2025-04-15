package com.example.vuiapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vuiapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

sealed class LoginUiState {
    object Loading : LoginUiState() // Represents the loading state
    data class Success(val keypass: String) : LoginUiState() // Represents successful login with a keypass
    data class Error(val message: String) : LoginUiState() // Represents an error with a message
    object Idle : LoginUiState() // Default idle state
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            try {
                val response = authRepository.login(username, password)
                if (response.isSuccessful) {
                    val keypass = response.body()?.keypass ?: ""
                    println("Keypass received: $keypass")
                    _uiState.value = LoginUiState.Success(keypass)
                } else {
                    _uiState.value = LoginUiState.Error("Invalid credentials!")
                }
            } catch (e: HttpException) {
                _uiState.value = LoginUiState.Error("Unexpected error: ${e.message}")
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error("Network error: ${e.message}")
            }
        }
    }
}