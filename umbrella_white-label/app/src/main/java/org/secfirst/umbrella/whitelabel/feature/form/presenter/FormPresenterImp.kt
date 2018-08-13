package org.secfirst.umbrella.whitelabel.feature.form.presenter

import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.experimental.launch
import org.secfirst.umbrella.whitelabel.data.ActiveForm
import org.secfirst.umbrella.whitelabel.data.Answer
import org.secfirst.umbrella.whitelabel.data.VirtualStorage
import org.secfirst.umbrella.whitelabel.feature.base.presenter.BasePresenterImp
import org.secfirst.umbrella.whitelabel.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.form.view.FormView
import org.secfirst.umbrella.whitelabel.misc.AppExecutors.Companion.uiContext
import org.secfirst.umbrella.whitelabel.misc.SchedulerProvider
import org.secfirst.umbrella.whitelabel.misc.asHTML
import org.secfirst.umbrella.whitelabel.misc.launchSilent
import javax.inject.Inject


class FormPresenterImp<V : FormView, I : FormBaseInteractor>
@Inject internal constructor(
        private val virtualStorage: VirtualStorage,
        interactor: I,
        schedulerProvider: SchedulerProvider,
        disposable: CompositeDisposable) : BasePresenterImp<V, I>(
        interactor = interactor,
        schedulerProvider = schedulerProvider,
        compositeDisposable = disposable), FormBasePresenter<V, I> {

    override fun submitShareFormHtml(activeForm: ActiveForm) {
        launch(uiContext) {
            val shareFile = virtualStorage.mountFilesystem(activeForm.asHTML(), activeForm.title)
            if (isActive)
                getView()?.showShareForm(shareFile)
        }
    }

    override fun submitDeleteActiveForm(activeForm: ActiveForm) {
        launchSilent(uiContext) {
            interactor?.deleteActiveForm(activeForm)
        }
    }

    override fun submitActiveForm(activeForm: ActiveForm) {
        launchSilent(uiContext) {
            interactor?.insertActiveForm(activeForm)
        }
    }

    override fun submitLoadAllForms() {
        launchSilent(uiContext) {
            interactor?.let {
                val activeForms = it.fetchActiveForms()
                val modelForms = it.fetchModalForms()
                activeForms.forEach { activeForm ->
                    modelForms.forEach { modelForm ->
                        if (activeForm.referenceId == modelForm.id)
                            activeForm.form = modelForm

                    }
                }
                if (isActive)
                    getView()?.showModelAndActiveForms(modelForms.toMutableList(),
                            activeForms.toMutableList())
            }
        }
    }

    override fun submitInsert(answer: Answer) {
        launchSilent(uiContext) {
            interactor?.insertFormData(answer)
        }
    }
}