package com.eniskaner.eyojcryptoapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.eyojcryptoapp.model.CurrencyUIModel
import com.eniskaner.eyojcryptoapp.repo.CyrptoRepository
import com.eniskaner.eyojcryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CyrptoListViewModel @Inject constructor(
    private val repository: CyrptoRepository
) : ViewModel() {

    var cryptoList = mutableStateOf<List<CurrencyUIModel>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        loadCryptos()
    }


    fun loadCryptos() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getCyrptoList()
            when(result) {
                is Resource.Success -> {
                    val cryptoItems = result.data!!.mapIndexed { index, item ->
                        CurrencyUIModel(
                            item.ticker,
                            item.symbol,
                            item.price,
                            item.changePercent24h,
                            item.isPositiveChange,
                            item.imageUrl,
                            item.directVolume,
                            item.totalVolume,
                            item.topTierVolume,
                            item.marketCapitalization,
                            item.lastUpdateTime,
                            item.isSelected
                            )
                    } as List<CurrencyUIModel>

                    errorMessage.value = ""
                    isLoading.value = false
                    cryptoList.value += cryptoItems
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
                is Resource.Loading -> {
                    errorMessage.value = ""
                }
            }
        }
    }

}

