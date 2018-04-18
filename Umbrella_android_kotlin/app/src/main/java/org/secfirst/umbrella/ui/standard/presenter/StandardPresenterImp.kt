package org.secfirst.umbrella.ui.standard.presenter

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import org.secfirst.umbrella.data.local.standard.Standard
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

    override fun onSubmitQuestion(standards: List<Standard>) {
        interactor.let {
            it?.getAuthorListInDatabase(standards)
            Log.i("test", "passei no presenter")
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