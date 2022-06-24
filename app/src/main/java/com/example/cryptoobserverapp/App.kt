package com.example.cryptoobserverapp

import android.app.Application
import androidx.room.Room
import com.example.cryptoobserverapp.data.local.CoinDatabase
import com.example.cryptoobserverapp.data.mapper.CoinMapper
import com.example.cryptoobserverapp.data.repository.CoinRepositoryImpl
import com.example.cryptoobserverapp.di.ApiFactory
import com.example.cryptoobserverapp.domain.repository.CoinRepository
import com.example.cryptoobserverapp.presentation.factory.CreateUseCase

class App : Application() {

    lateinit var useCase: CreateUseCase

    override fun onCreate() {
        super.onCreate()

        val db by lazy {
            Room.databaseBuilder(applicationContext, CoinDatabase::class.java, "coin_db")
                .fallbackToDestructiveMigration()
                .build()
        }

        val service = ApiFactory.apiService
        val mapper = CoinMapper()

        val repository: CoinRepository = CoinRepositoryImpl(db.coinDao(),service, mapper)

        useCase = CreateUseCase(repository)



    }
}