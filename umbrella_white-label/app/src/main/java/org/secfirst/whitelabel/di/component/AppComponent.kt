package org.secfirst.whitelabel.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import org.secfirst.whitelabel.UmbrellaApplication
import org.secfirst.whitelabel.di.builder.ActivityBuilder
import org.secfirst.whitelabel.di.module.AppModule
import org.secfirst.whitelabel.di.module.NetworkModule
import org.secfirst.whitelabel.di.module.RepositoryModule
import org.secfirst.whitelabel.di.module.TentContentModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class),
    (AppModule::class),
    (RepositoryModule::class),
    (NetworkModule::class),
    (TentContentModule::class),
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
