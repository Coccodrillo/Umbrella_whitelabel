package org.secfirst.umbrella.ui.standard.interactor

import android.content.Context
import org.secfirst.umbrella.data.database.standard.Standard
import org.secfirst.umbrella.data.database.standard.StandardRepo
import org.secfirst.umbrella.data.internal.TentRepo
import org.secfirst.umbrella.data.network.ApiHelper
import org.secfirst.umbrella.ui.base.interactor.BaseInteractorImp
import javax.inject.Inject


class StandardInteractorImp @Inject constructor(
        private val standardRepo: StandardRepo,
        private val tentRepo: TentRepo,
        apiHelper: ApiHelper) : BaseInteractorImp(apiHelper), StandardBaseInteractor {

    override fun getTentCategory(context: Context) = tentRepo.getAllTentCategory()

    override fun submitQuestion(standard: Standard): Long {
        tentRepo.getAllTentCategory()
//        tentRepo.getRepository()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { subscriber -> Log.e("test", "" + subscriber) }

        return standardRepo.insertStandard(standard)
    }

    override fun getBlogList() = apiHelper.getBlogApiCall()

    override fun getData() = standardRepo.getStandard()
}
