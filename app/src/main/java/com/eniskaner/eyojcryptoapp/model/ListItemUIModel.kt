package com.eniskaner.eyojcryptoapp.model

import androidx.recyclerview.widget.DiffUtil

class ListItemUIModel: ArrayList<CurrencyUIModel>()

data class CurrencyUIModel(
    val ticker: String,
    val symbol: String,
    val price: String,
    val changePercent24h: String,
    val isPositiveChange: Boolean,
    val imageUrl: String,
    val directVolume: String,
    val totalVolume: String,
    val topTierVolume: String,
    val marketCapitalization: String,
    val lastUpdateTime: Long,
    val isSelected: Boolean = false,
)

class ListItemUIModelDiffUtilCallback(
    private val oldList: List<CurrencyUIModel>,
    private val newList: List<CurrencyUIModel>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        if (oldItem is CurrencyUIModel && newItem is CurrencyUIModel) {
            return oldItem.ticker == newItem.ticker
        } else if (oldItem is CurrencyUIModel && newItem is CurrencyUIModel) {
            return true
        }
        return false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}
