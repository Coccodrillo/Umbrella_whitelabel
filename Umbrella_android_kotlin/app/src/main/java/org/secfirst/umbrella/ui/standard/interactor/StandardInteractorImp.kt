package org.secfirst.umbrella.ui.standard.interactor


import org.secfirst.umbrella.data.network.ApiHelper
import org.secfirst.umbrella.ui.base.interactor.BaseInteractorImp
import javax.inject.Inject

class StandardInteractorImp @Inject constructor(apiHelper: ApiHelper) : BaseInteractorImp(apiHelper), StandardBaseInteractor {

    override fun getBlogList() = apiHelper.getBlogApiCall()
}