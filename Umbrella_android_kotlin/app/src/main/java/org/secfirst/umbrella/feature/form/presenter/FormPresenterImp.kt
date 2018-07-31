package org.secfirst.umbrella.feature.form.presenter

import io.reactivex.disposables.CompositeDisposable
import org.secfirst.umbrella.data.Value
import org.secfirst.umbrella.feature.base.presenter.BasePresenterImp
import org.secfirst.umbrella.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.feature.form.view.FormView
import org.secfirst.umbrella.misc.AppExecutors.Companion.uiContext
import org.secfirst.umbrella.misc.SchedulerProvider
import org.secfirst.umbrella.misc.launchSilent
import javax.inject.Inject


class FormPresenterImp<V : FormView, I : FormBaseInteractor>
@Inject internal constructor(
        interactor: I,
        schedulerProvider: SchedulerProvider,
        disposable: CompositeDisposable) : BasePresenterImp<V, I>(
        interactor = interactor,
        schedulerProvider = schedulerProvider,
        compositeDisposable = disposable), FormBasePresenter<V, I> {


    override fun submitLoadActiveForms() {
        launchSilent(uiContext) {
            val allForms = interactor!!.fetchForm()
            val formsWithData = interactor!!.loadDataFormBy(allForms)
            if (isActive)
                getView()?.showActiveForms(formsWithData)
        }
    }

    override fun submitInsert(formData: Value) {
        launchSilent(uiContext) {
            interactor?.persisteFormData(formData)
        }
    }

    override fun submitLoadModelForms() {
        launchSilent(uiContext) {
            val forms = interactor!!.fetchForm()
            if (isActive)
                getView()?.showModelForms(forms)
        }
    }
}