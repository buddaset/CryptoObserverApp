package com.example.cryptoobserverapp.data.repository

import com.example.cryptoobserverapp.data.local.dao.CoinInfoDao
import com.example.cryptoobserverapp.data.mapper.CoinMapper
import com.example.cryptoobserverapp.data.remote.CoinService
import com.example.cryptoobserverapp.domain.model.CoinInfo
import com.example.cryptoobserverapp.domain.repository.CoinRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoinRepositoryImpl(
    private val coinDao: CoinInfoDao,
    private val service: CoinService,
    private val mapper: CoinMapper
): CoinRepository {

    override suspend fun getCoinInfo(fromSymbol: String): CoinInfo {
        return mapper.mapDbEntityToDomain(coinDao.getCoinDetails(fromSymbol))
    }

    override  fun getCoinInfoList(): Flow<List<CoinInfo>> =
        coinDao.getCoins().map { mapper.listMapDbEntityToDomain(it) }

    override suspend fun loadData() {
        while (true) {
            val topCoins = service.getCoins(limit = COIN_LIMIT )
            val fSyms = mapper.mapNamesListToString(topCoins)
            val  jsonContainer = service.getFullPriceList(fSyms = fSyms)
            val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
            val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbEntity(it) }
            coinDao.insertPriceList(dbModelList)
            delay(10_000)
        }


    }

    companion object {
        private  const val COIN_LIMIT = 50
    }

}