package org.secfirst.umbrella.ui.standard

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.secfirst.umbrella.ui.standard.view.StandardFragment

@Module
internal abstract class StandardProvider {

    @ContributesAndroidInjector(modules = [StandardModule::class])
    internal abstract fun provideStandardFactory(): StandardFragment
}