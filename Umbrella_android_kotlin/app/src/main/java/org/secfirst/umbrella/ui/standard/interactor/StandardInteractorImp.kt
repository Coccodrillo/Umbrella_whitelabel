package org.secfirst.umbrella.ui.standard.interactor

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.secfirst.core.logic.ElementAdapterImp
import org.secfirst.core.storage.TentStorageRepo
import org.secfirst.umbrella.data.database.standard.Standard
import org.secfirst.umbrella.data.database.standard.StandardRepo
import org.secfirst.umbrella.data.network.ApiHelper
import org.secfirst.umbrella.ui.base.interactor.BaseInteractorImp
import javax.inject.Inject


class StandardInteractorImp @Inject constructor(private val standardRepo: StandardRepo,
                                                apiHelper: ApiHelper,
                                                private val tentStorageRepo: TentStorageRepo,
                                                private val elementAdapterImp: ElementAdapterImp)
    : BaseInteractorImp(apiHelper), StandardBaseInteractor {

    override fun submitQuestion(standard: Standard): Long {
        tentStorageRepo.fetch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { _ -> elementAdapterImp.serialize() }
                .subscribe()

        return standardRepo.insertStandard(standard)
    }

    override fun getBlogList() = apiHelper.getBlogApiCall()

    override fun getData() = standardRepo.getStandard()
}
