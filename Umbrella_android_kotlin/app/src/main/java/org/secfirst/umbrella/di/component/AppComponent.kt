package org.secfirst.umbrella.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import org.secfirst.umbrella.UmbrellaApplication
import org.secfirst.umbrella.di.builder.ActivityBuilder
import org.secfirst.umbrella.di.module.AppModule
import org.secfirst.umbrella.di.module.NetworkModudule
import org.secfirst.umbrella.di.module.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class),
    (AppModule::class),
    (RepositoryModule::class),
    (NetworkModudule::class),
    (ActivityBuilder::class)])

interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: UmbrellaApplication)

}
