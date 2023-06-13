package com.eniskaner.eyojcryptoapp.di

import com.eniskaner.eyojcryptoapp.service.CyrptoAPI
import com.eniskaner.eyojcryptoapp.util.Constatnts.API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCyrptoApi() : CyrptoAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_URL)
            .build()
            .create(CyrptoAPI::class.java)
    }
}