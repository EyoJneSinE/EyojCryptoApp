package com.eniskaner.eyojcryptoapp.service

import retrofit2.http.GET
import retrofit2.http.Query

interface CyrptoAPI {

    //blockchain/list?api_key=30dc5a2a84148f5c9db0496f6f00e591562003bd496cea3a0e1617d0ddbcd13c
    @GET("blockchain/list")
    suspend fun getCyrptoAllList(
        @Query("api_key") api: String
    )

    //pricemultifull?fsyms=BTC&tsyms=USD&api_key=30dc5a2a84148f5c9db0496f6f00e591562003bd496cea3a0e1617d0ddbcd13c

    @GET("pricemultifull")
    suspend fun getCyrptoList(
        @Query("fsyms") fromSymbols: String = "BTC,ETH,LINK,EOS,BCH,LTC,XRP,OXT,B5V,TRX,XTZ,JST,ETC,SXP,ZEC,ALGO,WAVES,BAND,ADA,XLM,DOGE",
        @Query("tsyms") toSymbols: String = "USD",
        @Query("api_key") api : String
    ) : CyrptoList
}