package org.secfirst.umbrella.feature.form.presenter

import io.reactivex.disposables.CompositeDisposable
import org.secfirst.umbrella.feature.base.presenter.BasePresenterImp
import org.secfirst.umbrella.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.feature.form.view.FormBaseView
import org.secfirst.umbrella.util.SchedulerProvider
import org.secfirst.umbrella.util.trackException
import javax.inject.Inject

class FormPresenterImp<V : FormBaseView, I : FormBaseInteractor>
@Inject internal constructor(
        interactor: I,
        schedulerProvider: SchedulerProvider,
        disposable: CompositeDisposable) : BasePresenterImp<V, I>(
        interactor = interactor,
        schedulerProvider = schedulerProvider,
        compositeDisposable = disposable), FormBasePresenter<V, I> {

    override fun loadForms() {
        interactor?.let {
            it.fetchForm()
                    .compose(schedulerProvider.ioToMainSingleScheduler())
                    .trackException()
                    .map { forms -> getView()?.showForms(forms) }
                    .subscribe()
        }
    }
}