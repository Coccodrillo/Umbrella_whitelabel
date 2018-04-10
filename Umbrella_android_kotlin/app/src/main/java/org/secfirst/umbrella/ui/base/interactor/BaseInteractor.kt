package org.secfirst.umbrella.ui.base.interactor

import org.secfirst.umbrella.data.preferences.PreferenceHelper

open class BaseInteractor() : MVPInteractor {

    protected lateinit var preferenceHelper: PreferenceHelper

    constructor(preferenceHelper: PreferenceHelper) : this() {
        this.preferenceHelper = preferenceHelper
    }

    override fun isUserLoggedIn() = true

    override fun performUserLogout() = preferenceHelper.let {
        //stuff
    }

}