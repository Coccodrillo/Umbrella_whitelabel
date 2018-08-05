package org.secfirst.whitelabel.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.secfirst.whitelabel.feature.MainActivity


@Module
abstract class ActivityBuilder {

    //@ContributesAndroidInjector(modules = [(MainAtivityModule::class)])
    @ContributesAndroidInjector()
    abstract fun bindBaseActivity(): MainActivity
}

