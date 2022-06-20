package com.example.cryptoobserverapp.domain.usecase

import com.example.cryptoobserverapp.domain.model.CoinInfo
import com.example.cryptoobserverapp.domain.repository.CoinRepository

class GetCoinInfoListUseCase(private val repository: CoinRepository) {


    suspend operator fun invoke() : List<CoinInfo> =
        repository.getCoinInfoList()



}