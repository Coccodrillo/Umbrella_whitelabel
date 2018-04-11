package org.secfirst.umbrella.ui.standard.presenter

import io.reactivex.disposables.CompositeDisposable
import org.secfirst.umbrella.ui.base.presenter.BasePresenter
import org.secfirst.umbrella.ui.standard.interactor.StandardMVPInteractor
import org.secfirst.umbrella.ui.standard.view.StandardMVPView
import org.secfirst.umbrella.util.SchedulerProvider
import javax.inject.Inject

class StandardPresenter<V : StandardMVPView, I : StandardMVPInteractor>
@Inject internal constructor(
        interactor: I,
        schedulerProvider: SchedulerProvider,
        disposable: CompositeDisposable) : BasePresenter<V, I>(
        interactor = interactor,
        schedulerProvider = schedulerProvider,
        compositeDisposable = disposable), StandardMVPPresenter<V, I> {

    override fun onViewPrepared() {
        getView()?.showProgress()
        interactor?.let {
            it.getBlogList()
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe { blogResponse ->
                        getView()?.let {
                            it.hideProgress()
                            it.displayBlogList(blogResponse.data)
                        }
                    }
        }
    }


}