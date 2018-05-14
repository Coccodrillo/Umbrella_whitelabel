package org.secfirst.umbrella.ui.standard.presenter

import io.reactivex.disposables.CompositeDisposable
import org.secfirst.umbrella.data.database.standard.Standard
import org.secfirst.umbrella.ui.base.presenter.BasePresenterImp
import org.secfirst.umbrella.ui.standard.interactor.StandardBaseInteractor
import org.secfirst.umbrella.ui.standard.view.StandardBaseView
import org.secfirst.umbrella.util.SchedulerProvider
import javax.inject.Inject

class StandardPresenterImp<V : StandardBaseView, I : StandardBaseInteractor>
@Inject internal constructor(
        interactor: I,
        schedulerProvider: SchedulerProvider,
        disposable: CompositeDisposable) : BasePresenterImp<V, I>(
        interactor = interactor,
        schedulerProvider = schedulerProvider,
        compositeDisposable = disposable), StandardBasePresenter<V, I> {



    override fun onValidateInsertStandard(standard: Standard) {
        interactor.let {
            it!!.submitQuestion(standard)
        }
    }

    override fun getData() {
        interactor?.let {
            getView()?.displayData(it.getData())
        }
    }

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
