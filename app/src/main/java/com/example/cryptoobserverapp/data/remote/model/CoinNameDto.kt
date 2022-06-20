package com.example.cryptoobserverapp.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CoinNameDto (

    @SerialName("Name")
    val name: String
)