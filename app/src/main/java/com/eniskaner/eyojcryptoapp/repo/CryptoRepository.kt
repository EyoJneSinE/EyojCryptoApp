package com.eniskaner.eyojcryptoapp.repo

import com.eniskaner.eyojcryptoapp.model.Crypto
import com.eniskaner.eyojcryptoapp.model.CryptoAllList
import com.eniskaner.eyojcryptoapp.service.CryptoAPI
import com.eniskaner.eyojcryptoapp.util.Constatnts.CYRPTO_ID
import com.eniskaner.eyojcryptoapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api: CryptoAPI
) {
    suspend fun getCyrptoList() : Resource<CryptoAllList> {
        val response = try {
            api.getCyrptoList()
        } catch (e: Exception) {
            return Resource.Error("Error")
        }
        return Resource.Success(response)
    }

    suspend fun getCyrpto(id: String) : Resource<List<Crypto>> {
        val response = try {
            api.getCyrpto(CYRPTO_ID)
        } catch (e: Exception) {
            return Resource.Error("Error")
        }
        return Resource.Success(response)
    }
}