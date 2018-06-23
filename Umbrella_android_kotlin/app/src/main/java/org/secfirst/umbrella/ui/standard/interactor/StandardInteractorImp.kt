package org.secfirst.umbrella.ui.standard.interactor

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.secfirst.content.serialize.ElementViewer
import org.secfirst.content.storage.TentStorageRepo
import org.secfirst.umbrella.data.database.content.ContentRepo
import org.secfirst.umbrella.data.database.standard.Standard
import org.secfirst.umbrella.data.database.standard.StandardRepo
import org.secfirst.umbrella.data.network.ApiHelper
import org.secfirst.umbrella.ui.base.interactor.BaseInteractorImp
import javax.inject.Inject


class StandardInteractorImp @Inject constructor(private val standardRepo: StandardRepo,
                                                apiHelper: ApiHelper,
                                                private val tentStorageRepo: TentStorageRepo,
                                                private val elementViewer: ElementViewer,
                                                private val contentRepo: ContentRepo)
    : BaseInteractorImp(apiHelper), StandardBaseInteractor {

    override fun submitQuestion(standard: Standard): Long {
        tentStorageRepo.fetch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterSuccess { contentRepo.insertAllLessons(elementViewer.init()) }
                .subscribe()
        //contentRepo.getAllLessons().subscribe { it -> Log.e("test", "$it.categories.size") }
        return standardRepo.insertStandard(standard)
    }

    override fun getBlogList() = apiHelper.getBlogApiCall()

    override fun getData() = standardRepo.getStandard()
}
