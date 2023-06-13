package com.eniskaner.eyojcryptoapp.service

import com.eniskaner.eyojcryptoapp.model.ListItemUIModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CyrptoAPI {
    @GET("/pricemultifull")
    suspend fun getCyrptoList(
        @Query("fsyms") fromSymbols: String = "BTC,ETH,LINK,EOS,BCH,LTC,XRP,OXT,B5V,TRX,XTZ,JST,ETC,SXP,ZEC,ALGO,WAVES,BAND,ADA,XLM,DOGE",
        @Query("tsyms") toSymbols: String = "USD"
    ) : ListItemUIModel
}