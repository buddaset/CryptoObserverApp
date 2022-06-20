package com.example.cryptoobserverapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.cryptoobserverapp.data.mapper.CoinMapper
import com.example.cryptoobserverapp.data.remote.CoinService
import com.example.cryptoobserverapp.data.remote.response.CoinInfoJsonContainerResponse
import com.example.cryptoobserverapp.databinding.ActivityMainBinding
import com.example.cryptoobserverapp.di.ApiFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}