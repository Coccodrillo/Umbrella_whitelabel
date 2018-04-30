package org.secfirst.umbrella.ui.base.presenter

import org.secfirst.umbrella.ui.base.interactor.BaseInteractor
import org.secfirst.umbrella.ui.base.view.BaseView

interface BasePresenter<V : BaseView, I : BaseInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}
