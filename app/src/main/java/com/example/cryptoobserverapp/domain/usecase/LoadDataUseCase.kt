package com.example.cryptoobserverapp.domain.usecase

import com.example.cryptoobserverapp.domain.repository.CoinRepository

class LoadDataUseCase(private val repository: CoinRepository) {


    suspend operator fun invoke() = repository.loadData()

}