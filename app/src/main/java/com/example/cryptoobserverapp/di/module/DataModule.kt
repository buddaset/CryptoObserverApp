package com.example.cryptoobserverapp.di.module

import android.content.Context
import androidx.room.Room
import com.example.cryptoobserverapp.data.local.CoinDatabase
import com.example.cryptoobserverapp.data.local.dao.CoinInfoDao
import com.example.cryptoobserverapp.data.mapper.CoinMapper
import com.example.cryptoobserverapp.data.remote.CoinService
import com.example.cryptoobserverapp.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides


@Module
class DataModule {

    @Provides
    fun provideMapper(): CoinMapper = CoinMapper()


    @Provides
    fun provideCoinDao(appContext: Context): CoinInfoDao =
        CoinDatabase.getInstance(appContext).coinDao()




//    @Provides
//    fun provideCoinService(): CoinService =


}