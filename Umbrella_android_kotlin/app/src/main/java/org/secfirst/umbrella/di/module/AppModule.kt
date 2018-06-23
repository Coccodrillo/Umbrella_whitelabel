package org.secfirst.umbrella.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.secfirst.content.serialize.ElementAdapter
import org.secfirst.content.serialize.ElementLoader
import org.secfirst.content.serialize.ElementSerializer
import org.secfirst.content.serialize.ElementViewer
import org.secfirst.content.storage.TentConfig
import org.secfirst.content.storage.TentStorageDao
import org.secfirst.content.storage.TentStorageRepo
import org.secfirst.content.storage.TentStorageRepository
import org.secfirst.umbrella.data.database.content.ContentDao
import org.secfirst.umbrella.data.database.content.ContentRepo
import org.secfirst.umbrella.data.database.content.ContentRepository
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
    internal val tentDao
        get() = object : TentStorageDao {}

    @Provides
    @Singleton
    internal fun provideTentConfig(context: Context) = TentConfig(context.cacheDir.path + "/repo/")

    @Provides
    @Singleton
    internal fun provideTentRepo(tentConfig: TentConfig): TentStorageRepo = TentStorageRepository(tentDao, tentConfig)

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

    internal val standardDao
        get() = object : StandardDao {}

    internal val contentDao
        get() = object : ContentDao {}

    @Provides
    @Singleton
    internal fun provideContentDao(): ContentRepo = ContentRepository(contentDao)


    @Provides
    @Singleton
    internal fun provideApiStandardRepo(): StandardRepo = StandardRepository(standardDao)

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
