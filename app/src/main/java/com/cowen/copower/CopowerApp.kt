package com.cowen.copower

import android.app.Application
import com.cowen.copower.di.AppModule
import com.cowen.copower.di.challenges.ChallengesComponent
import com.cowen.copower.di.challenges.DaggerChallengesComponent

class CopowerApp : Application() {
    companion object {
        lateinit var challengeComponent: ChallengesComponent
    }

    override fun onCreate() {
        super.onCreate()
        challengeComponent = DaggerChallengesComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}