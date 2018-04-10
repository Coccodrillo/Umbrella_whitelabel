package org.secfirst.umbrella.ui.base.interactor

interface MVPInteractor {

    fun isUserLoggedIn(): Boolean

    fun performUserLogout()

}