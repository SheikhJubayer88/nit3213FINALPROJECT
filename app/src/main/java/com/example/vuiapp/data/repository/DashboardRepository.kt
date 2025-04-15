package com.example.vuiapp.data.repository

import com.example.vuiapp.data.api.ApiService
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getDashboard(keypass: String) = apiService.getDashboard(keypass)
}