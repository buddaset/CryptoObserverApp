package com.example.cryptoobserverapp.data.remote

import com.example.cryptoobserverapp.data.remote.response.CoinInfoJsonContainerResponse
import com.example.cryptoobserverapp.data.remote.response.CoinNamesResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface CoinService {


    @GET("data/top/totalvolfull")
    suspend fun getCoins(
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ):  CoinNamesResponse


    @GET("data/pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ): CoinInfoJsonContainerResponse


    companion object {

        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
    }
}
