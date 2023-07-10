package com.eniskaner.eyojcryptoapp.service

import com.eniskaner.eyojcryptoapp.model.Cyrpto
import com.eniskaner.eyojcryptoapp.model.CyrptoList
import com.eniskaner.eyojcryptoapp.util.Constatnts.CYRPTO_ID
import retrofit2.http.GET
import retrofit2.http.Query

interface CyrptoAPI {

    //https://api.coinpaprika.com/v1/coins
    @GET(".")
    suspend fun getCyrptoList() : CyrptoList

    //https://api.coinpaprika.com/v1/coins/btc-bitcoin
    @GET(".")
    suspend fun getCyrpto(
        @Query("") id : String
    ) : List<Cyrpto>
}