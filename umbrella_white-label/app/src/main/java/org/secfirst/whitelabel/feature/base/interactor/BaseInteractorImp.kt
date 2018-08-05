package org.secfirst.whitelabel.feature.base.interactor

import org.secfirst.whitelabel.data.network.ApiHelper

open class BaseInteractorImp() : BaseInteractor {

    protected lateinit var apiHelper: ApiHelper

    constructor(apiHelper: ApiHelper) : this() {
        this.apiHelper = apiHelper
    }

    override fun isUserLoggedIn() = true
}
