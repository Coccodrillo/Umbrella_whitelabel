package org.secfirst.umbrella.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import org.secfirst.umbrella.data.local.standard.StandardDao
import org.secfirst.umbrella.data.local.standard.StandardRepo
import org.secfirst.umbrella.data.local.standard.StandardRepository
import org.secfirst.umbrella.data.network.ApiHelper
import org.secfirst.umbrella.data.network.AppApiHelper
import org.secfirst.umbrella.data.network.NetworkEndPoint
import org.secfirst.umbrella.di.ApiKeyInfo
import org.secfirst.umbrella.util.SchedulerProvider
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

//    @Provides
//    @Singleton
//    internal fun provideAppDatabase(context: Context): AppDatabase =
//            Room.databaseBuilder(context, AppDatabase::class.java, "db_name").build()

    @Provides
    @ApiKeyInfo
    internal fun provideApiKey(): String = NetworkEndPoint.ENDPOINT_BLOG

    //QuestionRepo = QuestionRepository(appDatabase.questionsDao())

    internal val standardDao
        get() = object : StandardDao {}

    @Provides
    @Singleton
    internal fun provideApiStandardRepo(): StandardRepo = StandardRepository(standardDao)


    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()


}