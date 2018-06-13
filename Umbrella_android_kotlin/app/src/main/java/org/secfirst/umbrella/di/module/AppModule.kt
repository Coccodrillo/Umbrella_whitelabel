package org.secfirst.umbrella.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.secfirst.core.logic.ElementAdapter
import org.secfirst.core.logic.ElementLoader
import org.secfirst.core.logic.ElementSerializer
import org.secfirst.core.storage.TentConfig
import org.secfirst.core.storage.TentStorageDao
import org.secfirst.core.storage.TentStorageRepo
import org.secfirst.core.storage.TentStorageRepository
import org.secfirst.core.view.ElementViewer
import org.secfirst.umbrella.data.database.standard.StandardDao
import org.secfirst.umbrella.data.database.standard.StandardRepo
import org.secfirst.umbrella.data.database.standard.StandardRepository
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
class TentContentModule {

    @Provides
    @Singleton
    internal fun provideElement() = ElementSerializer()

    @Provides
    @Singleton
    internal fun provideElementLoader() = ElementLoader()

    @Provides
    @Singleton
    internal fun provideElementViewer(
            loader: ElementLoader,
            serializer: ElementSerializer,
            tentRepo: TentStorageRepo): ElementViewer = ElementAdapter(serializer, loader, tentRepo)

}


@Module
class RepositoryModule {

    internal val tentDao
        get() = object : TentStorageDao {}

    internal val standardDao
        get() = object : StandardDao {}


    @Provides
    @Singleton
    fun appDatabase(context: Context) = TentConfig(context)

    @Provides
    @Singleton
    internal fun provideApiStandardRepo(): StandardRepo = StandardRepository(standardDao)

    @Provides
    @Singleton
    internal fun provideTentGitInstance(context: Context): TentStorageRepo = TentStorageRepository(tentDao, appDatabase(context))
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
