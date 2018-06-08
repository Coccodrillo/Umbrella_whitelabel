package org.secfirst.umbrella.ui.standard.interactor

import org.secfirst.umbrella.data.database.standard.Standard
import org.secfirst.umbrella.data.database.standard.StandardRepo
import org.secfirst.umbrella.data.network.ApiHelper
import org.secfirst.core.storage.TentStorageRepo
import org.secfirst.umbrella.ui.base.interactor.BaseInteractorImp
import javax.inject.Inject


class StandardInteractorImp @Inject constructor(private val standardRepo: StandardRepo,
                                                apiHelper: ApiHelper,
                                                private val tentStorageRepo: TentStorageRepo)
    : BaseInteractorImp(apiHelper), StandardBaseInteractor {

    override fun submitQuestion(standard: Standard): Long {

//        tentRepo.getRepository()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { subscriber -> Log.e("test", "" + subscriber) }
// Observable.fromCallable { tentStorageRepo.fetch() }.doAfterNext { tentStorageRepo.parseFiles() }.subscribe()
        //Observable.fromCallable { tentStorageRepo.fetch() }.doAfterNext { tentStorageRepo.parseFiles() }.subscribe()
        tentStorageRepo.parseFiles()
        return standardRepo.insertStandard(standard)
    }

    override fun getBlogList() = apiHelper.getBlogApiCall()

    override fun getData() = standardRepo.getStandard()
}
