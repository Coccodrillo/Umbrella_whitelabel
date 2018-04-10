package org.secfirst.umbrella.ui.main.interactor

import org.secfirst.umbrella.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class MainInteractor
@Inject internal constructor() : BaseInteractor(), MainMVPInteractor {

    override fun getUserDetails() = Pair(preferenceHelper.getCurrentUserName(),
            preferenceHelper.getCurrentUserEmail())
}