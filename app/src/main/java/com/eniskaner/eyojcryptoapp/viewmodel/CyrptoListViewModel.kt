package com.eniskaner.eyojcryptoapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.eyojcryptoapp.model.CyrptoListItem
import com.eniskaner.eyojcryptoapp.repo.CyrptoRepository
import com.eniskaner.eyojcryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CyrptoListViewModel @Inject constructor(
    private val repository: CyrptoRepository
) : ViewModel() {

    var cryptoList = mutableStateOf<List<CyrptoListItem>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var initialCyrptoList = listOf<CyrptoListItem>()
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
                        val cryptoItems = result.data!!.mapIndexed { index, cyrptoListItem ->
                        CyrptoListItem(cyrptoListItem.id, cyrptoListItem.is_active, cyrptoListItem.is_new, cyrptoListItem.name,
                            cyrptoListItem.rank, cyrptoListItem.type, cyrptoListItem.symbol
                        )
                    } as List<CyrptoListItem>

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

