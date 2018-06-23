package org.secfirst.umbrella.feature.content.presenter

import io.reactivex.disposables.CompositeDisposable
import org.secfirst.umbrella.data.Lesson
import org.secfirst.umbrella.feature.base.presenter.BasePresenterImp
import org.secfirst.umbrella.feature.content.interactor.ContentBaseInteractor
import org.secfirst.umbrella.feature.content.view.ContentBaseView
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
                    .compose(schedulerProvider.ioToMainSingleScheduler())
                    .doAfterSuccess { contentInteractor.persist(contentInteractor.initParser()) }
                    .subscribe()


        }
    }

    override fun prepareLoadContent(lesson: Lesson) {
        interactor?.persist(lesson)
    }

}