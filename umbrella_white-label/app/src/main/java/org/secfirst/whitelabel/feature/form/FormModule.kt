package org.secfirst.whitelabel.feature.form

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import org.secfirst.whitelabel.di.module.AppModule
import org.secfirst.whitelabel.di.module.RepositoryModule
import org.secfirst.whitelabel.feature.form.interactor.FormBaseInteractor
import org.secfirst.whitelabel.feature.form.interactor.FormInteractorImp
import org.secfirst.whitelabel.feature.form.presenter.FormBasePresenter
import org.secfirst.whitelabel.feature.form.presenter.FormPresenterImp
import org.secfirst.whitelabel.feature.form.view.FormView
import org.secfirst.whitelabel.feature.form.view.controller.FormController
import org.secfirst.whitelabel.feature.form.view.controller.HostFormController
import javax.inject.Singleton


@Module
class FormModule {

    @Provides
    internal fun provideFormInteractor(interactor: FormInteractorImp): FormBaseInteractor = interactor

    @Provides
    internal fun provideFormPresenter(presenter: FormPresenterImp<FormView, FormBaseInteractor>)
            : FormBasePresenter<FormView, FormBaseInteractor> = presenter
}

@Singleton
@Component(modules = [FormModule::class,
    RepositoryModule::class,
    AppModule::class,
    AndroidInjectionModule::class])
interface FormComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): FormComponent
    }

    fun inject(hostFormController: HostFormController)

    fun inject(formController: FormController)
}
