package com.example.cryptoobserverapp.domain.usecase

import com.example.cryptoobserverapp.domain.model.CoinInfo
import com.example.cryptoobserverapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow


class GetCoinInfoListUseCase(private val repository: CoinRepository) {


    operator fun invoke() : Flow<List<CoinInfo>> =
        repository.getCoinInfoList()



}