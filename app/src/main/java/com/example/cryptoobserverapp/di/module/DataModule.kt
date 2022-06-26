package com.example.cryptoobserverapp.di.module

import android.content.Context
import com.example.cryptoobserverapp.data.local.CoinDatabase
import com.example.cryptoobserverapp.data.local.dao.CoinInfoDao
import com.example.cryptoobserverapp.data.mapper.CoinMapper
import com.example.cryptoobserverapp.data.remote.CoinService
import com.example.cryptoobserverapp.data.worker.RefreshDataWorkerFactory
import com.example.cryptoobserverapp.di.scope.ApplicationScope
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit


@Module
class DataModule {

    @Provides
    fun provideMapper(): CoinMapper = CoinMapper()


    @Provides
    fun provideCoinDao(appContext: Context): CoinInfoDao =
        CoinDatabase.getInstance(appContext).coinDao()


    @ApplicationScope
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()

    @Provides
    fun provideJsonFactory(): Converter.Factory {
        val json = Json { ignoreUnknownKeys = true }
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    fun provideOkHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        interceptors.forEach { clientBuilder.addInterceptor(it) }
        return clientBuilder.build()
    }

    @Provides
    fun provideInterceptors():ArrayList<Interceptor> {
        val interceptors = arrayListOf<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        interceptors.add(loggingInterceptor)
        return interceptors
    }

    @ApplicationScope
    @Provides
    fun provideCoinService(retrofit: Retrofit): CoinService =
        retrofit.create(CoinService::class.java)


    @Provides
    fun provideRefreshDataWorkerFactory(coinDao: CoinInfoDao, service: CoinService, mapper: CoinMapper) : RefreshDataWorkerFactory =
        RefreshDataWorkerFactory(coinDao, service, mapper)


    companion object {
        private const val BASE_URL = "https://min-api.cryptocompare.com/"
    }


}