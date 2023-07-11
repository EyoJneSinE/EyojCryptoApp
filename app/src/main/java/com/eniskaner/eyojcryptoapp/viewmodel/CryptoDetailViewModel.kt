package com.eniskaner.eyojcryptoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.eniskaner.eyojcryptoapp.model.Crypto
import com.eniskaner.eyojcryptoapp.repo.CryptoRepository
import com.eniskaner.eyojcryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    suspend fun getCyrpto(id: String) : Resource<List<Crypto>> {
        return repository.getCyrpto(id)
    }
}