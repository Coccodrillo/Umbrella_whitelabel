package org.secfirst.umbrella.feature.content

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.secfirst.umbrella.feature.content.view.ContentFragment

@Module
internal abstract class ContentProvider {

    @ContributesAndroidInjector(modules = [ContentModule::class])
    internal abstract fun provideContentFactory(): ContentFragment
}
