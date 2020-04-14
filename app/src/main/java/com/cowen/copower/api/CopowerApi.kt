package com.cowen.copower.api

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface  CopowerApi {
    @GET("challenges")
    fun getChallenges(@Query("after") after: String,
               @Query("limit") limit: String): Call<ChallengesResponse>

    @GET("challenges")
    suspend fun getDeferredChallenges(@Query("after") after: String,
               @Query("limit") limit: String): ChallengesResponse
}