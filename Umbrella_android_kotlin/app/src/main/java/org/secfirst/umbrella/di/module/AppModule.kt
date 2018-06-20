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
import org.secfirst.umbrella.data.network.ApiHelper
import org.secfirst.umbrella.data.network.NetworkEndPoint.BASE_URL
import org.secfirst.umbrella.data.storage.TentConfig
import org.secfirst.umbrella.data.storage.TentStorageDao
import org.secfirst.umbrella.data.storage.TentStorageRepo
import org.secfirst.umbrella.data.storage.TentStorageRepository
import org.secfirst.umbrella.core.serialize.ElementAdapter
import org.secfirst.umbrella.core.serialize.ElementLoader
import org.secfirst.umbrella.core.serialize.ElementSerializer
import org.secfirst.umbrella.core.serialize.ElementViewer
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
    internal fun provideTentConfig(context: Context) = TentConfig(context.cacheDir.path + "/repo/")

    @Provides
    @Singleton
    internal fun provideApiStandardRepo(): StandardRepo = StandardRepository(standardDao)

    @Provides
    @Singleton
    internal fun provideTentGitInstance(context: Context, tentConfig: TentConfig): TentStorageRepo = TentStorageRepository(tentDao, tentConfig)
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
