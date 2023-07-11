package com.eniskaner.eyojcryptoapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.eyojcryptoapp.model.CryptoAllListItem
import com.eniskaner.eyojcryptoapp.repo.CryptoRepository
import com.eniskaner.eyojcryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    var cryptoList = mutableStateOf<List<CryptoAllListItem>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var initialCyrptoList = listOf<CryptoAllListItem>()
    private var isSearchStarting = true

    init {
        loadCryptos()
    }

    fun searchCyrptoList(query : String) {
        val listToSearch = if(isSearchStarting) {
            cryptoList.value
        } else {
            initialCyrptoList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                cryptoList.value = initialCyrptoList
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.symbol.contains(query.trim(), ignoreCase = true)
            }
            if(isSearchStarting) {
                initialCyrptoList = cryptoList.value
                isSearchStarting = false
            }
            cryptoList.value = results
        }
    }
    fun loadCryptos() {
        viewModelScope.launch {
            val result = repository.getCyrptoList()
            when(result) {
                is Resource.Success -> {
                        val cryptoItems = result.data!!.mapIndexed { index, item ->
                        CryptoAllListItem(item.beta_value, item.circulating_supply, item.first_data_at, item.id,
                            item.last_updated, item.max_supply, item.name, item.quotes, item.rank, item.symbol, item.total_supply
                        )
                    } as List<CryptoAllListItem>

                    errorMessage.value = ""
                    isLoading.value = false
                    cryptoList.value += cryptoItems

                    //cryptoList.value += cryptoItems
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

