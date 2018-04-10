package org.secfirst.umbrella.ui.main.interactor

import org.secfirst.umbrella.ui.base.interactor.MVPInteractor

interface MainMVPInteractor : MVPInteractor {

    fun getUserDetails(): Pair<String?, String?>
}