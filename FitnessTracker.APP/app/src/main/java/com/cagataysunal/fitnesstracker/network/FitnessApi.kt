package com.cagataysunal.fitnesstracker.network

import com.cagataysunal.fitnesstracker.Constants
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object FitnessApi {
    private val json = Json { ignoreUnknownKeys = true }

    private const val CONTENT_TYPE = "application/json; charset=utf-8"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(
                json.asConverterFactory(CONTENT_TYPE.toMediaType())
            )
            .build()
    }

    val service: FitnessService = retrofit.create(FitnessService::class.java)
}