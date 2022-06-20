package com.example.cryptoobserverapp.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinInfoDto(
    @SerialName("FROMSYMBOL")
    val fromSymbol: String,
    @SerialName("TOSYMBOL")
    val toSymbol: String,
    @SerialName("PRICE")
    val price: String,
    @SerialName("LASTUPDATE")
    val lastUpdate: Long,
    @SerialName("HIGHDAY")
    val highDay: String,
    @SerialName("LOWDAY")
    val lowDay: String,
    @SerialName("LASTMARKET")
    val lastMarket: String,
    @SerialName("IMAGEURL")
    val imageUrl: String
)
