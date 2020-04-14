package com.cowen.copower.api

import com.cowen.copower.commons.Logger
import kotlinx.coroutines.Deferred
import retrofit2.Call
import javax.inject.Inject

class ChallengesRestApi @Inject constructor(private val copowerApi: CopowerApi) : ChallengesApi {

    override suspend fun getChallenges(after: String, limit: String): ChallengesResponse {
        Logger.dt("calling Rest API")
        return copowerApi.getDeferredChallenges(after, limit)
    }

    override fun getChallengesOldApi(after: String, limit: String): Call<ChallengesResponse> {
        return copowerApi.getChallenges(after, limit)
    }
}