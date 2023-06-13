package com.eniskaner.eyojcryptoapp.model

import com.google.gson.annotations.SerializedName

data class CryptoCompareResponse(
    @SerializedName("RAW")
    val raw: Map<String, Map<String, RawCurrency>>,
    @SerializedName("DISPLAY")
    val display: Map<String, Map<String, DisplayCurrency>>
)

data class RawCurrency(
    @SerializedName("PRICE")
    val price: Float,
    @SerializedName("LASTUPDATE")
    val lastUpdate: Long,
    @SerializedName("FROMSYMBOL")
    val fromSymbols: String,
    @SerializedName("CHANGEPCT24HOUR")
    val changePercent24h: Float,
    @SerializedName("IMAGEURL")
    val imageUrl: String
)

data class DisplayCurrency(
    @SerializedName("FROMSYMBOL")
    val fromSymbols: String,
    @SerializedName("PRICE")
    val price: String,
    @SerializedName("CHANGEPCT24HOUR")
    val changePercent24h: String,
    @SerializedName("VOLUME24HOURTO")
    val directVolume: String,
    @SerializedName("TOTALVOLUME24HTO")
    val totalVolume: String,
    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    val topTierVolume: String,
    @SerializedName("MKTCAP")
    val marketCapitalization: String
)
