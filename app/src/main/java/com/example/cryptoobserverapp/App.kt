package com.example.cryptoobserverapp

import android.app.Application
import androidx.room.Room
import com.example.cryptoobserverapp.data.local.CoinDatabase
import com.example.cryptoobserverapp.data.local.dao.CoinInfoDao
import com.example.cryptoobserverapp.data.mapper.CoinMapper
import com.example.cryptoobserverapp.data.repository.CoinRepositoryImpl
import com.example.cryptoobserverapp.di.ApiFactory
import com.example.cryptoobserverapp.domain.repository.CoinRepository
import com.example.cryptoobserverapp.presentation.factory.CreateUseCase

class App : Application() {

    lateinit var useCase: CreateUseCase
    lateinit var coinDao: CoinInfoDao

    override fun onCreate() {
        super.onCreate()

        val db = CoinDatabase.getInstance(applicationContext)

        coinDao = db.coinDao()

        val service = ApiFactory.apiService
        val mapper = CoinMapper()

        val repository: CoinRepository = CoinRepositoryImpl(db.coinDao(), mapper, applicationContext)

        useCase = CreateUseCase(repository)



    }
}