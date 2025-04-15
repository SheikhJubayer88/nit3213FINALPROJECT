package com.example.vuiapp.data.repository

import com.example.vuiapp.data.api.ApiService
import com.example.vuiapp.data.api.LoginRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun login(username: String, password: String) =
        apiService.login(LoginRequest(username, password))
}