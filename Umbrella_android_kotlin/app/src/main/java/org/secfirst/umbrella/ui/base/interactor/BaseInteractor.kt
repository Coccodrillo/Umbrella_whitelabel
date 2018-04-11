package org.secfirst.umbrella.ui.base.interactor

import org.secfirst.umbrella.data.remote.ApiHelper

open class BaseInteractor() : MVPInteractor {

    protected lateinit var apiHelper: ApiHelper

    constructor(apiHelper: ApiHelper) : this() {
        this.apiHelper = apiHelper
    }

    override fun isUserLoggedIn() = true
}