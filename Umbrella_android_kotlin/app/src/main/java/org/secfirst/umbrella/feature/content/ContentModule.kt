package org.secfirst.umbrella.feature.content

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import org.secfirst.umbrella.feature.content.interactor.ContentBaseInteractor
import org.secfirst.umbrella.feature.content.interactor.ContentInteractorImp
import org.secfirst.umbrella.feature.content.presenter.ContentBasePresenter
import org.secfirst.umbrella.feature.content.presenter.ContentPresenterImp
import org.secfirst.umbrella.feature.content.view.ContentBaseView
import org.secfirst.umbrella.feature.content.view.ContentFragment


@Module
class ContentModule {

    @Provides
    internal fun provideContentInteractor(interactor: ContentInteractorImp): ContentBaseInteractor = interactor

    @Provides
    internal fun provideContentPresenter(presenter: ContentPresenterImp<ContentBaseView, ContentBaseInteractor>)
            : ContentBasePresenter<ContentBaseView, ContentBaseInteractor> = presenter

}

@Module
internal abstract class ContentProvider {

    @ContributesAndroidInjector(modules = [ContentModule::class])
    internal abstract fun provideContentFactory(): ContentFragment
}
