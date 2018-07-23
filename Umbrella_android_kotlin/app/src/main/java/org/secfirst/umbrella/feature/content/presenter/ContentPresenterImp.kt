package org.secfirst.umbrella.feature.content.presenter

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.secfirst.umbrella.data.Root
import org.secfirst.umbrella.feature.base.presenter.BasePresenterImp
import org.secfirst.umbrella.feature.content.interactor.ContentBaseInteractor
import org.secfirst.umbrella.feature.content.view.ContentBaseView
import org.secfirst.umbrella.util.SchedulerProvider
import org.secfirst.umbrella.util.trackException
import javax.inject.Inject


class ContentPresenterImp<V : ContentBaseView, I : ContentBaseInteractor>
@Inject internal constructor(
        interactor: I,
        schedulerProvider: SchedulerProvider,
        disposable: CompositeDisposable) : BasePresenterImp<V, I>(
        interactor = interactor,
        schedulerProvider = schedulerProvider,
        compositeDisposable = disposable), ContentBasePresenter<V, I> {

    override fun validateLoadAllLesson() {}

    override fun manageContent() {
        interactor?.let { contentInteractor ->
            Single.fromCallable { validateFetch(contentInteractor.fetchData()) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .trackException()
                    .doAfterSuccess {
                        validateData()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .trackException()
                                .doAfterSuccess { getView()!!.finishDownloadedData() }
                                .subscribe()
                    }
                    .subscribe()
        }
    }

    private fun validateFetch(fetchResult: Single<Boolean>) {
        fetchResult
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .trackException()
                .subscribe()
    }

    private fun validateData(): Single<Root> {
        return Single.create<Root> { emitter ->
            val root = interactor?.initParser()
            if (root != null && root.elements.isNotEmpty()) {
                interactor?.persist(root)
                emitter.onSuccess(root)
            } else emitter.onError(Throwable("Content not found."))
        }
    }
}