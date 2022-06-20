package com.example.cryptoobserverapp.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

data class CoinInfoJsonContainerResponse(
    @SerialName("RAW")
    val json: JsonObject
)
