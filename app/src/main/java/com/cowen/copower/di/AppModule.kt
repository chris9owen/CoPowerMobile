package com.cowen.copower.di

import android.app.Application
import android.content.Context
import com.cowen.copower.CopowerApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: CopowerApp) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication(): Application = app
}