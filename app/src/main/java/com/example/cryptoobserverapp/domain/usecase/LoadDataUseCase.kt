package com.example.cryptoobserverapp.domain.usecase

import com.example.cryptoobserverapp.domain.repository.CoinRepository

class LoadDataUseCase(private val repository: CoinRepository) {


     operator fun invoke() = repository.loadData()

}