package com.example.cryptoobserverapp.domain.repository

import com.example.cryptoobserverapp.domain.model.CoinInfo

interface CoinRepository {

    suspend fun getCoinInfo(fromSymbol: String): CoinInfo

    suspend fun getCoinInfoList(): List<CoinInfo>
}