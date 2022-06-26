package com.example.cryptoobserverapp.data.worker

import android.content.Context
import androidx.work.*
import com.example.cryptoobserverapp.App
import com.example.cryptoobserverapp.data.local.CoinDatabase
import com.example.cryptoobserverapp.data.mapper.CoinMapper
import com.example.cryptoobserverapp.data.repository.CoinRepositoryImpl
import com.example.cryptoobserverapp.di.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker (
    context: Context,
    workerParameters: WorkerParameters
        ): CoroutineWorker(context, workerParameters) {

    private val coinDao = CoinDatabase.getInstance(context).coinDao()
    private val service = ApiFactory.apiService


    private val mapper = CoinMapper()


    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = service.getCoins(limit = COIN_LIMIT)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val  jsonContainer = service.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbEntity(it) }
                coinDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10_000)
        }


    }

    companion object {

        const val WORKER_NAME = "update_data_worker"
        private const val COIN_LIMIT = 50

        fun makeOneTimeWorkRequest(): OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<RefreshDataWorker>()
                .build()
    }
}