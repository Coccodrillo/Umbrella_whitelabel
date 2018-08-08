//package org.secfirst.whitelabel.feature.content.presenter
//
//import io.reactivex.Single
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.schedulers.Schedulers
//import org.secfirst.whitelabel.data.Root
//import org.secfirst.whitelabel.feature.base.presenter.BasePresenterImp
//import org.secfirst.whitelabel.feature.tour.interactor.TourBaseInteractor
//import org.secfirst.whitelabel.feature.content.view.ContentBaseView
//import org.secfirst.whitelabel.feature.tour.presenter.TourBasePresenter
//import org.secfirst.whitelabel.feature.tour.view.TourView
//import org.secfirst.whitelabel.misc.SchedulerProvider
//import javax.inject.Inject
//
//
//class TourPresenterImp<V : TourView, I : TourBaseInteractor>
//@Inject internal constructor(
//        interactor: I,
//        schedulerProvider: SchedulerProvider,
//        disposable: CompositeDisposable) : BasePresenterImp<V, I>(
//        interactor = interactor,
//        schedulerProvider = schedulerProvider,
//        compositeDisposable = disposable), TourBasePresenter<V, I> {
//
//    override fun validateLoadAllLesson() {}
//
//    override fun manageContent() {
//        interactor?.let { contentInteractor ->
//            Single.fromCallable { validateFetch(contentInteractor.fetchData()) }
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doAfterSuccess {
//                        validateData()
//                                .subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .doAfterSuccess { getView()!!.finishDownloadedData() }
//                                .subscribe()
//                    }
//                    .subscribe()
//        }
//    }
//
//    private fun validateFetch(fetchResult: Single<Boolean>) {
//        fetchResult
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe()
//    }
//
//    private fun validateData(): Single<Root> {
//        return Single.create<Root> { emitter ->
//            val root = interactor?.initParser()
//            if (root != null && root.elements.isNotEmpty()) {
//                interactor?.persist(root)
//                emitter.onSuccess(root)
//            } else emitter.onError(Throwable("Content not found."))
//        }
//    }
//}