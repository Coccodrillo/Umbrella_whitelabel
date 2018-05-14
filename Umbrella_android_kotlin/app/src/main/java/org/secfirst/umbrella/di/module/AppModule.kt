package org.secfirst.umbrella.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.secfirst.umbrella.data.database.standard.StandardDao
import org.secfirst.umbrella.data.database.standard.StandardRepo
import org.secfirst.umbrella.data.database.standard.StandardRepository
import org.secfirst.umbrella.data.internal.TentDao
import org.secfirst.umbrella.data.internal.TentRepo
import org.secfirst.umbrella.data.internal.TentRepository
import org.secfirst.umbrella.data.network.ApiHelper
import org.secfirst.umbrella.data.network.NetworkEndPoint.BASE_URL
import org.secfirst.umbrella.util.SchedulerProvider
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

}

@Module
class RepositoryModule {

    internal val standardDao
        get() = object : StandardDao {}

    @Provides
    @Singleton
    internal fun provideApiStandardRepo(): StandardRepo = StandardRepository(standardDao)

    internal val tentDao
        get() = object : TentDao {}

    @Provides
    @Singleton
    internal fun provideTentGitInstance(context: Context): TentRepo = TentRepository(tentDao, context)
}

@Module
class NetworkModule {

    @Provides
    @Reusable
    internal fun providePostApi(retrofit: Retrofit): ApiHelper {
        return retrofit.create(ApiHelper::class.java)
    }

    @Provides
    @Reusable
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}
