package com.example.cryptoobserverapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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


    companion object {

        private var db : CoinDatabase? = null

        fun getInstance(context: Context) : CoinDatabase {
            synchronized(this) {
                db?.let { return it }
                val instance = Room.databaseBuilder(context, CoinDatabase::class.java, "coin_db")
                    .fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance

            }
        }

    }
}