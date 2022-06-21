package com.example.cryptoobserverapp.data.mapper

import com.example.cryptoobserverapp.data.local.model.CoinInfoDbEntity
import com.example.cryptoobserverapp.data.remote.model.CoinInfoDto
import com.example.cryptoobserverapp.data.remote.response.CoinInfoJsonContainerResponse
import com.example.cryptoobserverapp.data.remote.response.CoinNamesResponse
import com.example.cryptoobserverapp.domain.model.CoinInfo
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







    fun mapNamesListToString(nameListResponse: CoinNamesResponse): String =
        nameListResponse.data
            .map { containerName ->
                containerName.coinInfo.name
            }
            .joinToString { "," }


    fun mapDbEntityToDomain(dbEntity: CoinInfoDbEntity): CoinInfo {
        return CoinInfo(
            fromSymbol = dbEntity.fromSymbol,
            toSymbol = dbEntity.toSymbol,
            price = dbEntity.price,
            lastUpdate = dbEntity.lastUpdate,
            highDay = dbEntity.highDay,
            lowDay = dbEntity.lowDay,
            lastMarket = dbEntity.lastMarket,
            imageUrl = dbEntity.imageUrl

        )
    }

    fun listMapDbEntityToDomain(coins: List<CoinInfoDbEntity>): List<CoinInfo> =
        coins.map { mapDbEntityToDomain(it) }

}