package com.eniskaner.eyojcryptoapp.service

import com.eniskaner.eyojcryptoapp.model.Crypto
import com.eniskaner.eyojcryptoapp.model.CryptoAllList
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoAPI {

    //https://api.coinpaprika.com/v1/tickers
    @GET(".")
    suspend fun getCyrptoList() : CryptoAllList

    //https://api.coinpaprika.com/v1/coins/btc-bitcoin
    @GET(".")
    suspend fun getCyrpto(
        @Query(".") id : String
    ) : List<Crypto>
}