package com.example.cryptoobserverapp.data.worker

import android.content.Context
import androidx.work.*
import com.example.cryptoobserverapp.data.local.dao.CoinInfoDao
import com.example.cryptoobserverapp.data.mapper.CoinMapper
import com.example.cryptoobserverapp.data.remote.CoinService
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinDao: CoinInfoDao,
    private val service: CoinService,
    private val mapper: CoinMapper
) : CoroutineWorker(context, workerParameters) {


    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = service.getCoins(limit = COIN_LIMIT)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = service.getFullPriceList(fSyms = fSyms)
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

    class Factory(
        private val coinDao: CoinInfoDao,
        private val service: CoinService,
        private val mapper: CoinMapper
    ) : ChildWorkerFactory {
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker =
            RefreshDataWorker(context, workerParameters, coinDao, service, mapper)

    }


}