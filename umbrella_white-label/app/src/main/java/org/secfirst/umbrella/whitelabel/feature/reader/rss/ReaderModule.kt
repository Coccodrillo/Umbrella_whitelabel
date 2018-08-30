package org.secfirst.umbrella.whitelabel.feature.reader.rss

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import org.secfirst.umbrella.whitelabel.di.builder.ActivityBuilder
import org.secfirst.umbrella.whitelabel.di.module.AppModule
import org.secfirst.umbrella.whitelabel.di.module.NetworkModule
import org.secfirst.umbrella.whitelabel.di.module.RepositoryModule
import org.secfirst.umbrella.whitelabel.di.module.TentContentModule
import org.secfirst.umbrella.whitelabel.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.form.interactor.FormInteractorImp
import org.secfirst.umbrella.whitelabel.feature.form.presenter.FormBasePresenter
import org.secfirst.umbrella.whitelabel.feature.form.presenter.FormPresenterImp
import org.secfirst.umbrella.whitelabel.feature.form.view.FormView
import org.secfirst.umbrella.whitelabel.feature.reader.rss.interactor.RssBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.reader.rss.interactor.RssInteractorImp
import org.secfirst.umbrella.whitelabel.feature.reader.rss.presenter.RssBasePresenter
import org.secfirst.umbrella.whitelabel.feature.reader.rss.presenter.RssPresenterImp
import org.secfirst.umbrella.whitelabel.feature.reader.rss.view.RssController
import org.secfirst.umbrella.whitelabel.feature.reader.rss.view.RssView
import org.secfirst.umbrella.whitelabel.feature.tour.TourModule
import javax.inject.Singleton


@Module
class ReaderModule {

    @Provides
    internal fun provideReaderInteractor(interactor: RssInteractorImp): RssBaseInteractor = interactor

    @Provides
    internal fun provideReaderPresenter(presenter: RssPresenterImp<RssView, RssBaseInteractor>)
            : RssBasePresenter<RssView, RssBaseInteractor> = presenter
}

@Singleton
@Component(modules = [ReaderModule::class,
    (AppModule::class),
    (RepositoryModule::class),
    (NetworkModule::class),
    (ActivityBuilder::class)])
interface ReanderComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ReanderComponent
    }

    fun inject(rssController: RssController)
}
