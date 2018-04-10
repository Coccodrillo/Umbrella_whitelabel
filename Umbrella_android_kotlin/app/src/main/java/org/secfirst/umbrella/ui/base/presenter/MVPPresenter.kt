package org.secfirst.umbrella.ui.base.presenter

import org.secfirst.umbrella.ui.base.interactor.MVPInteractor
import org.secfirst.umbrella.ui.base.view.MVPView

interface MVPPresenter<V : MVPView, I : MVPInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}