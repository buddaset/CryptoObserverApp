package com.example.cryptoobserverapp.data.remote.response

import com.example.cryptoobserverapp.data.remote.model.CoinNameContainer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinNamesResponse(
    @SerialName("Data")
    val data: List<CoinNameContainer>
)
