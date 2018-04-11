package org.secfirst.umbrella.ui.standard.interactor


import org.secfirst.umbrella.data.remote.ApiHelper
import org.secfirst.umbrella.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class StandardInteractor @Inject constructor(apiHelper: ApiHelper) : BaseInteractor(apiHelper), StandardMVPInteractor {

    override fun getBlogList() = apiHelper.getBlogApiCall()
}