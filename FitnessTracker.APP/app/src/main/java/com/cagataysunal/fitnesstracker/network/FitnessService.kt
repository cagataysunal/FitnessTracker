package com.cagataysunal.fitnesstracker.network

import com.cagataysunal.fitnesstracker.model.LoginRequest
import com.cagataysunal.fitnesstracker.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface FitnessService {
    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}