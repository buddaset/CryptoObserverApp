package com.example.cryptoobserverapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "coins")
data class CoinInfoDbEntity (
        @PrimaryKey(autoGenerate = false)
        val fromSymbol : String,
        val toSymbol: String,
        val price: String,
        val lastUpdate: Long,
        val highDay: String,
        val lowDay: String,
        val lastMarket: String,
        val imageUrl: String

    )

