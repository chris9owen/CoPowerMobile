package com.cowen.copower.di.challenges

import com.cowen.copower.api.ChallengesApi
import com.cowen.copower.api.ChallengesRestApi
import com.cowen.copower.api.CopowerApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ChallengesModule {

    @Provides
    @Singleton
    fun provideChallengeApi(copowerApi: CopowerApi): ChallengesApi = ChallengesRestApi(copowerApi)

    @Provides
    @Singleton
    fun provideCopowerApi(retrofit: Retrofit): CopowerApi = retrofit.create(CopowerApi::class.java)
}