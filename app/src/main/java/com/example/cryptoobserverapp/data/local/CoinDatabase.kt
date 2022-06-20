package com.example.cryptoobserverapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptoobserverapp.data.local.dao.CoinInfoDao
import com.example.cryptoobserverapp.data.local.model.CoinInfoDbEntity


@Database(
    entities = [CoinInfoDbEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CoinDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinInfoDao
}