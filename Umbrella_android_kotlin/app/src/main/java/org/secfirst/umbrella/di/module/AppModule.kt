package org.secfirst.umbrella.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import org.secfirst.umbrella.BuildConfig
import org.secfirst.umbrella.data.local.AppDatabase
import org.secfirst.umbrella.data.remote.ApiHelper
import org.secfirst.umbrella.data.remote.AppApiHelper
import org.secfirst.umbrella.di.ApiKeyInfo
import org.secfirst.umbrella.util.SchedulerProvider

import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "db_name").build()

    @Provides
    @ApiKeyInfo
    internal fun provideApiKey(): String = BuildConfig.API_KEY


    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()


}