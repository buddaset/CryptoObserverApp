package com.example.cryptoobserverapp.di

import com.example.cryptoobserverapp.data.remote.CoinService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

object ApiFactory {

    private const val BASE_URL = "https://min-api.cryptocompare.com/"
    const val BASE_IMAGE_URL = "https://cryptocompare.com"

    private val loggingInterceptor =  HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val json = Json { ignoreUnknownKeys = true}
    private val contentType = "application/json".toMediaType()

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

    val apiService : CoinService by lazy { retrofit.create() }
}
