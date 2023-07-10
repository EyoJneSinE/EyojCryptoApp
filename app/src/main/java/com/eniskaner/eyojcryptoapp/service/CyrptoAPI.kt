package com.eniskaner.eyojcryptoapp.service

import com.eniskaner.eyojcryptoapp.util.Constatnts.CYRPTO_ID
import retrofit2.http.GET
import retrofit2.http.Query

interface CyrptoAPI {

    //https://api.coinpaprika.com/v1/coins
    @GET(".")
    suspend fun getCyrptoList()

    //https://api.coinpaprika.com/v1/coins/btc-bitcoin
    @GET(CYRPTO_ID)
    suspend fun getCyrpto()
}