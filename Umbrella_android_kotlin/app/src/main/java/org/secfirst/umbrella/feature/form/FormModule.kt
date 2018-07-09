package org.secfirst.umbrella.feature.form

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import org.secfirst.umbrella.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.feature.form.interactor.FormInteractorImp
import org.secfirst.umbrella.feature.form.presenter.FormBasePresenter
import org.secfirst.umbrella.feature.form.presenter.FormPresenterImp
import org.secfirst.umbrella.feature.form.view.FormBaseView
import org.secfirst.umbrella.feature.form.view.FormFragment


@Module
class FormModule {

    @Provides
    internal fun provideFormInteractor(interactor: FormInteractorImp): FormBaseInteractor = interactor

    @Provides
    internal fun provideFormPresenter(presenter: FormPresenterImp<FormBaseView, FormBaseInteractor>)
            : FormBasePresenter<FormBaseView, FormBaseInteractor> = presenter

}

@Module
internal abstract class FormProvider {

    @ContributesAndroidInjector(modules = [FormModule::class])
    internal abstract fun provideFormFactory(): FormFragment
}