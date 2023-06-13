package com.eniskaner.eyojcryptoapp.repo

import com.eniskaner.eyojcryptoapp.model.ListItemUIModel
import com.eniskaner.eyojcryptoapp.service.CyrptoAPI
import com.eniskaner.eyojcryptoapp.util.Constatnts.API_KEY
import com.eniskaner.eyojcryptoapp.util.Resource
import javax.inject.Inject

class CyrptoRepository @Inject constructor(
    private val api: CyrptoAPI
) {
    suspend fun getCyrptoList() : Resource<ListItemUIModel> {
        val response = try {
            api.getCyrptoList(API_KEY)
        } catch (e: Exception) {
            return Resource.Error("Error")
        }
        return Resource.Success(response)
    }

    suspend fun getCyrpto(fromSymbols: String) : Resource<ListItemUIModel> {
        val response = try {
            api.getCyrptoList()
        } catch (e: Exception) {
            return Resource.Error("Error")
        }
        return Resource.Success(response)
    }
}