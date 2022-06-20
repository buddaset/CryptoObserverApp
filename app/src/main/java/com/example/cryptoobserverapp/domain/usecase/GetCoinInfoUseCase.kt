package com.example.cryptoobserverapp.domain.usecase

import com.example.cryptoobserverapp.domain.model.CoinInfo
import com.example.cryptoobserverapp.domain.repository.CoinRepository

class GetCoinInfoUseCase(private val repository: CoinRepository) {


    suspend operator fun invoke(fromSymbol: String) : CoinInfo =
        repository.getCoinInfo(fromSymbol)

}