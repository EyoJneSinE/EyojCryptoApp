package com.eniskaner.eyojcryptoapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.eyojcryptoapp.model.RawUsd
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

    var cryptoList = mutableStateOf<List<RawUsd>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var initialCyrptoList = listOf<RawUsd>()
    private var isSearchStarting = true

    init {
        loadCryptos("BTC")
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
                it.fromsymbol.contains(query.trim(), ignoreCase = true)
            }
            if(isSearchStarting) {
                initialCyrptoList = cryptoList.value
                isSearchStarting = false
            }
            cryptoList.value = results
        }
    }
    fun loadCryptos(fromSymbols: String) {
        viewModelScope.launch {
            val result = repository.getCyrptoList()
            when(result) {
                is Resource.Success -> {
                    val cryptoItems = result.data!!.mapIndexed { index, item ->
                        RawUsd(item.type, item.market, item.fromsymbol, item.tosymbol, item.flags, item.price, item.lastupdate,
                            item.median, item.lastvolume, item.lastvolumeto, item.lasttradeid, item.volumeday, item.volumedayto,
                            item.volume24Hour, item.volume24Hourto, item.openday, item.highday, item.lowday, item.open24Hour, item.high24Hour,
                            item.low24Hour, item.lastmarket, item.volumehour, item.volumehourto, item.openhour, item.highhour, item.lowhour,
                            item.toptiervolume24Hour, item.toptiervolume24Hourto, item.change24Hour, item.changepct24Hour, item.changeday,
                            item.changepctday, item.changehour, item.changepcthour, item.conversiontype, item.conversionsymbol,
                            item.conversionlastupdate, item.supply, item.mktcap, item.mktcappenalty, item.circulatingsupply, item.circulatingsupplymktcap,
                            item.totalvolume24H, item.totalvolume24Hto, item.totaltoptiervolume24H, item.totaltoptiervolume24Hto, item.imageurl
                        )
                    } as List<RawUsd>

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

