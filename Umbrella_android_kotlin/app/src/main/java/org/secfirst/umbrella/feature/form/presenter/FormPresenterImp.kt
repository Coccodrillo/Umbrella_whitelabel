package org.secfirst.umbrella.feature.form.presenter

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value
import org.secfirst.umbrella.feature.base.presenter.BasePresenterImp
import org.secfirst.umbrella.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.feature.form.view.FormView
import org.secfirst.umbrella.util.SchedulerProvider
import org.secfirst.umbrella.util.trackException
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
        compositeDisposable.add(prepareActiveForms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .trackException()
                .subscribe { activeForms ->
                    getView()?.showActiveForms(activeForms)
                })
    }

    private fun prepareActiveForms(): Single<List<Form>> {
        val allForms = interactor!!.fetchForm()
        val activeForms = mutableListOf<Form>()
        allForms.forEach { form ->
            val value = interactor!!.loadDataFormById(form.id)
            if (value.isNotEmpty()) {
                form.data = value
                activeForms.add(form)
            }
        }
        return Single.just(activeForms)
    }

    override fun submitInsert(formData: Value) {
        Single.fromCallable { interactor!!.saveForm(formData) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .trackException()
                .subscribe()
    }

    override fun submitLoadModelForms() {
        compositeDisposable.add(Single.just(interactor!!.fetchForm())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .trackException()
                .subscribe { forms ->
                    getView()?.showModelForms(forms)
                })
    }
}