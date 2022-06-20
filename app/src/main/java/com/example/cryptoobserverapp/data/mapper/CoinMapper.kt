package com.example.cryptoobserverapp.data.mapper

import com.example.cryptoobserverapp.data.local.model.CoinInfoDbEntity
import com.example.cryptoobserverapp.data.remote.model.CoinInfoDto
import com.example.cryptoobserverapp.data.remote.response.CoinInfoJsonContainerResponse
import com.example.cryptoobserverapp.data.remote.response.CoinNamesResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject

class CoinMapper {

    fun mapDtoToDbEntity(dto: CoinInfoDto): CoinInfoDbEntity =
        CoinInfoDbEntity(
            fromSymbol = dto.fromSymbol,
            toSymbol = dto.toSymbol,
            price = dto.price,
            lastUpdate = dto.lastUpdate,
            highDay = dto.highDay,
            lowDay = dto.lowDay,
            lastMarket = dto.lastMarket,
            imageUrl = dto.imageUrl
        )

    @OptIn(ExperimentalSerializationApi::class)
    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerResponse): List<CoinInfoDto> {
        /*
        parse json  ---   {"BTC":{"USD":{"TYPE":"5","MARKET":"CCCAGG".... } } }
         */
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject: JsonObject = jsonContainer.json
        val keysCoin = jsonObject.values
        for (keyCoin in keysCoin) {
            val currencyKeys = keyCoin.jsonObject.values
            for (currencyKey in currencyKeys) {
                result.add(json.decodeFromJsonElement(currencyKey))
            }
        }
        return result
    }

    fun mapNamesListToString(nameListDto: CoinNamesResponse): String =
        nameListDto.data
            .map { containerName ->
                containerName.coinInfo.name
            }
            .joinToString { "," }

}