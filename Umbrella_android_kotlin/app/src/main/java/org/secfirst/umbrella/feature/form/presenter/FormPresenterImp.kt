package org.secfirst.umbrella.feature.form.presenter

import io.reactivex.disposables.CompositeDisposable
import org.secfirst.umbrella.data.Answer
import org.secfirst.umbrella.data.Form
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

    override fun submitForm(form: Form) {
        launchSilent(uiContext) {
            interactor?.insertForm(form)
        }
    }


    override fun submitLoadActiveForms() {
        launchSilent(uiContext) {
            var activeForms = listOf<Form>()
            interactor?.let {
                activeForms = it.fetchActiveForms()
                activeForms.forEach { form ->
                    form.screens = it.fetchScreenBy(form.referenceId).toMutableList()
                }
            }
            if (isActive)
                getView()?.showActiveForms(activeForms)
        }
    }

    override fun submitInsert(answer: Answer) {
        launchSilent(uiContext) {
            interactor?.insertFormData(answer)
        }
    }

    override fun submitLoadModelForms() {
        launchSilent(uiContext) {
            if (isActive)
                getView()?.showModelForms(interactor?.fetchForms())
        }
    }
}