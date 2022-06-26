package com.example.cryptoobserverapp.data.repository

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoobserverapp.data.local.dao.CoinInfoDao
import com.example.cryptoobserverapp.data.mapper.CoinMapper
import com.example.cryptoobserverapp.data.remote.CoinService
import com.example.cryptoobserverapp.data.worker.RefreshDataWorker
import com.example.cryptoobserverapp.domain.model.CoinInfo
import com.example.cryptoobserverapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoinRepositoryImpl(
    private val coinDao: CoinInfoDao,
    private val mapper: CoinMapper,
    private val appContext: Context
) : CoinRepository {

    override suspend fun getCoinInfo(fromSymbol: String): CoinInfo {
        return mapper.mapDbEntityToDomain(coinDao.getCoinDetails(fromSymbol))
    }

    override fun getCoinInfoList(): Flow<List<CoinInfo>> =
        coinDao.getCoins().map { mapper.listMapDbEntityToDomain(it) }

    override fun loadData() {
        val workManager = WorkManager.getInstance(appContext)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.WORKER_NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeOneTimeWorkRequest()
        )


    }

    companion object {
        const val COIN_LIMIT = 50
    }

}