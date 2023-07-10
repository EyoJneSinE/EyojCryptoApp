package com.eniskaner.eyojcryptoapp.repo

import com.eniskaner.eyojcryptoapp.model.Cyrpto
import com.eniskaner.eyojcryptoapp.model.CyrptoList
import com.eniskaner.eyojcryptoapp.service.CyrptoAPI
import com.eniskaner.eyojcryptoapp.util.Constatnts.CYRPTO_ID
import com.eniskaner.eyojcryptoapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CyrptoRepository @Inject constructor(
    private val api: CyrptoAPI
) {
    suspend fun getCyrptoList() : Resource<CyrptoList> {
        val response = try {
            api.getCyrptoList()
        } catch (e: Exception) {
            return Resource.Error("Error")
        }
        return Resource.Success(response)
    }

    suspend fun getCyrpto(id: String) : Resource<List<Cyrpto>> {
        val response = try {
            api.getCyrpto(CYRPTO_ID)
        } catch (e: Exception) {
            return Resource.Error("Error")
        }
        return Resource.Success(response)
    }
}