package org.secfirst.umbrella.feature.base.view

import dagger.Module
import dagger.Provides
import org.secfirst.umbrella.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.feature.form.interactor.FormInteractorImp
import org.secfirst.umbrella.feature.form.presenter.FormBasePresenter
import org.secfirst.umbrella.feature.form.presenter.FormPresenterImp
import org.secfirst.umbrella.feature.form.view.FormBaseView
import org.secfirst.umbrella.feature.form.view.FormController
//
//@Module
//class MainAtivityModule {
//
//    @Provides
//    internal fun provideFormInteractor(interactor: FormInteractorImp): FormBaseInteractor = interactor
//
//    @Provides
//    internal fun provideFormPresenter(presenter: FormPresenterImp<FormBaseView, FormBaseInteractor>)
//            : FormBasePresenter<FormBaseView, FormBaseInteractor> = presenter
//
//    @Provides
//    internal fun provideFormController(presenter: FormPresenterImp<FormBaseView, FormBaseInteractor>): FormController {
//        val formController = FormController()
//        formController.inject(presenter)
//        return formController
//    }
//}
