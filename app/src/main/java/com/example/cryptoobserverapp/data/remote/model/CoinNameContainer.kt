package com.example.cryptoobserverapp.data.remote.model

import kotlinx.serialization.SerialName

data class CoinNameContainer(
    @SerialName("CoinInfo")
    val coinInfo: CoinNameDto
)