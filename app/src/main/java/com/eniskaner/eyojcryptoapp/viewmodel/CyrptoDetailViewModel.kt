package com.eniskaner.eyojcryptoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.eniskaner.eyojcryptoapp.repo.CyrptoRepository
import com.eniskaner.eyojcryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CyrptoDetailViewModel @Inject constructor(
    private val repository: CyrptoRepository
) : ViewModel() {

    suspend fun getCyrpto(fromSymbols: String) : Resource<CyrptoList> {
        return repository.getCyrpto(fromSymbols)
    }
}