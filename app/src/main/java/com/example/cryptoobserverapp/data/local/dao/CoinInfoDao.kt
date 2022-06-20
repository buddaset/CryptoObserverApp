package com.example.cryptoobserverapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptoobserverapp.data.local.model.CoinInfoDbEntity

interface CoinInfoDao {

    @Query("SELECT * FROM coins ORDER BY lastUpdate DESC")
   suspend fun getCoins(): List<CoinInfoDbEntity>

    @Query("SELECT * FROM coins WHERE fromSymbol == :fromSymbol LIMIT 1")
    suspend fun getCoinDetails(fromSymbol: String): CoinInfoDbEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(priceList: List<CoinInfoDbEntity>)


}