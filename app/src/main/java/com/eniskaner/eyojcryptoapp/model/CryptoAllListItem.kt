package com.eniskaner.eyojcryptoapp.model

data class CryptoAllListItem(
    val beta_value: Double,
    val circulating_supply: Long,
    val first_data_at: String,
    val id: String,
    val last_updated: String,
    val max_supply: Long,
    val name: String,
    val quotes: Quotes,
    val rank: Int,
    val symbol: String,
    val total_supply: Long
)