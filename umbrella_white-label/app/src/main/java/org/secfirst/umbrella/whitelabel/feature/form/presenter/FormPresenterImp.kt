package org.secfirst.umbrella.whitelabel.feature.form.presenter

import io.reactivex.disposables.CompositeDisposable
import org.secfirst.umbrella.whitelabel.data.Answer
import org.secfirst.umbrella.whitelabel.data.Form
import org.secfirst.umbrella.whitelabel.feature.base.presenter.BasePresenterImp
import org.secfirst.umbrella.whitelabel.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.form.view.FormView
import org.secfirst.umbrella.whitelabel.misc.AppExecutors.Companion.uiContext
import org.secfirst.umbrella.whitelabel.misc.SchedulerProvider
import org.secfirst.umbrella.whitelabel.misc.launchSilent
import javax.inject.Inject


class FormPresenterImp<V : FormView, I : FormBaseInteractor>
@Inject internal constructor(
        interactor: I,
        schedulerProvider: SchedulerProvider,
        disposable: CompositeDisposable) : BasePresenterImp<V, I>(
        interactor = interactor,
        schedulerProvider = schedulerProvider,
        compositeDisposable = disposable), FormBasePresenter<V, I> {


    override fun submitDeleteForm(form: Form) {
        launchSilent(uiContext) {
            interactor?.deleteForm(form)
        }
    }

    override fun submitForm(form: Form) {
        launchSilent(uiContext) {
            interactor?.insertForm(form)
        }
    }

    override fun submitLoadAllForms() {
        launchSilent(uiContext) {
            interactor?.let {
                val activeForms = it.fetchActiveForms()
                activeForms.forEach { form ->
                    form.screens = it.fetchScreenBy(form.referenceId).toMutableList()
                }
                val modelForms = it.fethModalForms()
                if (isActive)
                getView()?.showModelAndActiveForms(modelForms,activeForms)
            }
        }
    }

    override fun submitInsert(answer: Answer) {
        launchSilent(uiContext) {
            interactor?.insertFormData(answer)
        }
    }
}