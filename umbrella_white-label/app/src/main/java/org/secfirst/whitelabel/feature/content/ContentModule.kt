//package org.secfirst.whitelabel.feature.content
//
//import android.app.Application
//import dagger.BindsInstance
//import dagger.Component
//import dagger.Module
//import dagger.Provides
//import dagger.android.AndroidInjectionModule
//import org.secfirst.whitelabel.di.builder.ActivityBuilder
//import org.secfirst.whitelabel.di.module.AppModule
//import org.secfirst.whitelabel.di.module.NetworkModule
//import org.secfirst.whitelabel.di.module.RepositoryModule
//import org.secfirst.whitelabel.di.module.TentContentModule
//import org.secfirst.whitelabel.feature.content.interactor.ContentBaseInteractor
//import org.secfirst.whitelabel.feature.tour.interactor.TourBaseInteractor
//import org.secfirst.whitelabel.feature.tour.interactor.TourInteractorImp
//import org.secfirst.whitelabel.feature.tour.presenter.TourBasePresenter
//import org.secfirst.whitelabel.feature.tour.presenter.TourPresenterImp
//import org.secfirst.whitelabel.feature.content.view.ContentBaseView
//import org.secfirst.whitelabel.feature.content.view.ContentController
//import javax.inject.Singleton
//
//
//@Module
//class ContentModule {
//
//    @Provides
//    internal fun provideContentInteractor(interactor: TourInteractorImp): TourBaseInteractor = interactor
//
//    @Provides
//    internal fun provideContentPresenter(presenter: TourPresenterImp<ContentBaseView, ContentBaseInteractor>)
//            : TourBasePresenter<ContentBaseView, ContentBaseInteractor> = presenter
//
//}
//
//@Singleton
//@Component(modules = [(AndroidInjectionModule::class),
//    (AppModule::class),
//    (RepositoryModule::class),
//    (NetworkModule::class),
//    (TentContentModule::class),
//    (ContentModule::class),
//    (ActivityBuilder::class)])
//interface ContentComponent {
//
//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun application(application: Application): Builder
//
//        fun build(): ContentComponent
//    }
//
//    fun inject(contentController: ContentController)
//}
