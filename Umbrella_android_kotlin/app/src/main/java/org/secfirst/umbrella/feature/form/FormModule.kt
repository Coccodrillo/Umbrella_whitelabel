package org.secfirst.umbrella.feature.form

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.di.module.AppModule
import org.secfirst.umbrella.di.module.RepositoryModule
import org.secfirst.umbrella.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.feature.form.interactor.FormInteractorImp
import org.secfirst.umbrella.feature.form.presenter.FormBasePresenter
import org.secfirst.umbrella.feature.form.presenter.FormPresenterImp
import org.secfirst.umbrella.feature.form.view.FormBaseView
import org.secfirst.umbrella.feature.form.view.FormController
import org.secfirst.umbrella.feature.form.view.FormInputController
import org.secfirst.umbrella.feature.form.view.adapter.FormInputAdapter
import javax.inject.Singleton


@Module
class FormModule {

    @Provides
    internal fun provideFormInteractor(interactor: FormInteractorImp): FormBaseInteractor = interactor

    @Provides
    internal fun provideFormPresenter(presenter: FormPresenterImp<FormBaseView, FormBaseInteractor>)
            : FormBasePresenter<FormBaseView, FormBaseInteractor> = presenter

    @Provides
    fun provideFormEditAdapter(controller: FormInputController, form: Form) = FormInputAdapter(form, controller)

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

    fun inject(formController: FormController)
}
