package com.example.cryptoobserverapp.data.remote.response

import com.example.cryptoobserverapp.data.remote.model.CoinNameContainer
import kotlinx.serialization.SerialName

data class CoinNamesResponse(
    @SerialName("data")
    val data: List<CoinNameContainer>
)
