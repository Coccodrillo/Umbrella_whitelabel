package org.secfirst.umbrella.ui.content.presenter

import io.reactivex.disposables.CompositeDisposable
import org.secfirst.umbrella.ui.base.presenter.BasePresenterImp
import org.secfirst.umbrella.ui.content.ContentBaseView
import org.secfirst.umbrella.ui.content.interactor.ContentBaseInteractor
import org.secfirst.umbrella.util.SchedulerProvider
import javax.inject.Inject

class ContentPresenterImp<V : ContentBaseView, I : ContentBaseInteractor>
@Inject internal constructor(
        interactor: I,
        schedulerProvider: SchedulerProvider,
        disposable: CompositeDisposable) : BasePresenterImp<V, I>(
        interactor = interactor,
        schedulerProvider = schedulerProvider,
        compositeDisposable = disposable), ContentBasePresenter<V, I> {

    override fun manageContent() {
        interactor?.let { contentInteractor ->
            contentInteractor.fetchData()
            contentInteractor.persistLesson(contentInteractor.serializeRootToLesson())
        }
    }

    override fun getLesson() {

    }


}