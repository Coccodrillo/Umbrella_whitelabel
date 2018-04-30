package org.secfirst.umbrella.ui.standard.interactor


import org.secfirst.umbrella.data.local.standard.Standard
import org.secfirst.umbrella.data.local.standard.StandardRepo
import org.secfirst.umbrella.data.network.ApiHelper
import org.secfirst.umbrella.ui.base.interactor.BaseInteractorImp
import javax.inject.Inject

class StandardInteractorImp @Inject constructor(
        private val standardRepo: StandardRepo,
        apiHelper: ApiHelper) : BaseInteractorImp(apiHelper), StandardBaseInteractor {


    override fun submitQuestion(standard: Standard) = standardRepo.insertStandard(standard)

    override fun getBlogList() = apiHelper.getBlogApiCall()

    override fun getData() = standardRepo.getStandard()
}
