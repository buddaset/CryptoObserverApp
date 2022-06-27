package com.example.cryptoobserverapp.di.module

import androidx.work.ListenableWorker
import com.example.cryptoobserverapp.data.local.dao.CoinInfoDao
import com.example.cryptoobserverapp.data.mapper.CoinMapper
import com.example.cryptoobserverapp.data.remote.CoinService
import com.example.cryptoobserverapp.data.worker.ChildWorkerFactory
import com.example.cryptoobserverapp.data.worker.CoinWorkerFactory
import com.example.cryptoobserverapp.data.worker.RefreshDataWorker
import com.example.cryptoobserverapp.di.key.WorkerKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import kotlin.reflect.KClass


@Module
class WorkerModule {


    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    @Provides
    fun provideRefreshDataWorkerFactory( coinDao: CoinInfoDao, coinService: CoinService, mapper: CoinMapper): ChildWorkerFactory =
        RefreshDataWorker.Factory(coinDao, coinService, mapper)

    @Provides
    fun providerCoinWorkerFactory(workerProvides: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>) : CoinWorkerFactory =
        CoinWorkerFactory(workerProvides)



}