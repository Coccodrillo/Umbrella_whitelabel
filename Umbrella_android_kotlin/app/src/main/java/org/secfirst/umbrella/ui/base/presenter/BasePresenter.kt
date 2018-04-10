package org.secfirst.umbrella.ui.base.presenter

import io.reactivex.disposables.CompositeDisposable
import org.secfirst.umbrella.ui.base.interactor.MVPInteractor
import org.secfirst.umbrella.ui.base.view.MVPView
import org.secfirst.umbrella.util.SchedulerProvider

abstract class BasePresenter<V : MVPView, I : MVPInteractor>
internal constructor(protected var interactor: I?,
                     protected val schedulerProvider: SchedulerProvider,
                     protected val compositeDisposable: CompositeDisposable) : MVPPresenter<V, I> {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
        interactor = null
    }

}