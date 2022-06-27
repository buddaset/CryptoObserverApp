package com.example.cryptoobserverapp.domain.repository

import com.example.cryptoobserverapp.domain.model.CoinInfo
import kotlinx.coroutines.flow.Flow


interface CoinRepository {

    suspend fun getCoinInfo(fromSymbol: String): CoinInfo

    fun getCoinInfoList(): Flow<List<CoinInfo>>

     fun loadData()


}