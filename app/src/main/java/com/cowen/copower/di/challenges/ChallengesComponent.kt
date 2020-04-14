package com.cowen.copower.di.challenges

import com.cowen.copower.di.AppModule
import com.cowen.copower.di.NetworkModule
import com.cowen.copower.features.challenges.ChallengesFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Component for Challenges
 */
@Singleton
@Component(modules = [AppModule::class, ChallengesModule::class, NetworkModule::class])
interface ChallengesComponent {
    fun inject(challengesFragment: ChallengesFragment)

}