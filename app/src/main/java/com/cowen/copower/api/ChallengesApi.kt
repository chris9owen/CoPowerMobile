package com.cowen.copower.api

import kotlinx.coroutines.Deferred
import retrofit2.Call

/**
 * ChallengesAPI
 *
 *
 */
interface ChallengesApi {
    fun getChallengesOldApi(after: String, limit: String): Call<ChallengesResponse>
    suspend fun getChallenges(after: String, limit: String): ChallengesResponse
}