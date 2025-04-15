package com.example.vuiapp.data.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val keypass: String)

@Parcelize
data class Entity(
    val name: String,
    val culture: String,
    val domain: String,
    val symbol: String,
    val parentage: String,
    val romanEquivalent: String,
    val description: String
) : Parcelable

data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)

interface ApiService {
    @POST("sydney/auth") // Replace with correct endpoint depending on location
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("dashboard/{keypass}")
    suspend fun getDashboard(@Path("keypass") keypass: String): Response<DashboardResponse>
}