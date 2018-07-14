package org.secfirst.umbrella.feature.form

import dagger.Component
import dagger.Module
import dagger.Provides
import org.secfirst.umbrella.di.module.AppModule
import org.secfirst.umbrella.di.module.RepositoryModule
import org.secfirst.umbrella.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.feature.form.interactor.FormInteractorImp
import org.secfirst.umbrella.feature.form.presenter.FormBasePresenter
import org.secfirst.umbrella.feature.form.presenter.FormPresenterImp
import org.secfirst.umbrella.feature.form.view.FormBaseView
import org.secfirst.umbrella.feature.form.view.FormController
import javax.inject.Singleton


@Module
class FormModule {

    @Provides
    internal fun provideFormInteractor(interactor: FormInteractorImp): FormBaseInteractor = interactor

    @Provides
    internal fun provideFormPresenter(presenter: FormPresenterImp<FormBaseView, FormBaseInteractor>)
            : FormBasePresenter<FormBaseView, FormBaseInteractor> = presenter

}

@Singleton
@Component(modules = [FormModule::class, RepositoryModule::class, AppModule::class])
interface FormComponent {
    fun inject(fragmentController: FormController)
}
