package com.eniskaner.eyojcryptoapp.model

import androidx.recyclerview.widget.DiffUtil

sealed class ListItemUIModel

data class SeekUIModel(val frequency: Int) : ListItemUIModel()

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
) : ListItemUIModel()

class ListItemUIModelDiffUtilCallback(
    private val oldList: List<ListItemUIModel>,
    private val newList: List<ListItemUIModel>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        if (oldItem is CurrencyUIModel && newItem is CurrencyUIModel) {
            return oldItem.ticker == newItem.ticker
        } else if (oldItem is SeekUIModel && newItem is SeekUIModel) {
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
