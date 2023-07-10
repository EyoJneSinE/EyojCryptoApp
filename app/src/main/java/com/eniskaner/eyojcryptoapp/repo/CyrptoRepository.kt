package com.eniskaner.eyojcryptoapp.repo

import com.eniskaner.eyojcryptoapp.service.CyrptoAPI
import com.eniskaner.eyojcryptoapp.util.Constatnts.API_KEY
import com.eniskaner.eyojcryptoapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CyrptoRepository @Inject constructor(
    private val api: CyrptoAPI
) {
    suspend fun getCyrptoList() : Resource<CyrptoList> {
        val response = try {
            api.getCyrptoList(fromSymbols = "0XBTC,1ST,1WO,AAC,ABCC,ABT,ABYSS,ACCN,ACE,ADA,ADB,ADH,ADI,ADL,ADT,ADX,AE,AEN,AERGO,AGI,AGVC,AID,AIDOC,AIT,AIX,ALIS,ALX,AMB,AMLT,AMM,AMN,\n" +
                    "AMO,ANKR,ANT,AOA,APIS,APPC,ARAW,ARBT,ARCT,ARN,ART,ARY,AST,ASTO,ATL,ATM,ATMI,ATS,AUC,AVH,AVT,AWC,AXPR,B2B,BAAS,BANCA,BAT,BAX,BBC,BBO,BCAP,\n" +
                    "BCDN,BCDT,BCH,BCPT,BDG,BEAT", toSymbols = "USD", API_KEY)
        } catch (e: Exception) {
            return Resource.Error("Error")
        }
        return Resource.Success(response)
    }

    suspend fun getCyrpto(fromSymbols: String) : Resource<CyrptoList> {
        val response = try {
            api.getCyrptoList(fromSymbols, toSymbols = "USD", API_KEY )
        } catch (e: Exception) {
            return Resource.Error("Error")
        }
        return Resource.Success(response)
    }
}