package org.secfirst.umbrella.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.secfirst.umbrella.ui.main.MainActivity
import org.secfirst.umbrella.ui.standard.StandardProvider

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(StandardProvider::class)])
    abstract fun bindFeedActivity(): MainActivity
}
