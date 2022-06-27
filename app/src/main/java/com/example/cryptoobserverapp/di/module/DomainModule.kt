package com.example.cryptoobserverapp.di.module

import android.content.Context
import com.example.cryptoobserverapp.data.local.dao.CoinInfoDao
import com.example.cryptoobserverapp.data.mapper.CoinMapper
import com.example.cryptoobserverapp.data.repository.CoinRepositoryImpl
import com.example.cryptoobserverapp.domain.repository.CoinRepository
import com.example.cryptoobserverapp.domain.usecase.GetCoinInfoListUseCase
import com.example.cryptoobserverapp.domain.usecase.GetCoinInfoUseCase
import com.example.cryptoobserverapp.domain.usecase.LoadDataUseCase
import dagger.Module
import dagger.Provides


@Module
class DomainModule {

    @Provides
    fun provideCoinRepository(
        coinDao: CoinInfoDao,
        mapper: CoinMapper,
        appContext: Context
    ): CoinRepository = CoinRepositoryImpl(coinDao, mapper, appContext)


    @Provides
    fun provideGetConInfoListUseCase(repository: CoinRepository): GetCoinInfoListUseCase =
        GetCoinInfoListUseCase(repository)

    @Provides
    fun provideGetCoinInfoUseCase(repository: CoinRepository): GetCoinInfoUseCase =
        GetCoinInfoUseCase(repository)

    @Provides
    fun provideLoadDataUseCase(repository: CoinRepository): LoadDataUseCase =
        LoadDataUseCase(repository)
}