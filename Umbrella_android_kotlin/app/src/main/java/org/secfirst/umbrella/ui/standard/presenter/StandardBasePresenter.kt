package org.secfirst.umbrella.ui.standard.presenter

import org.secfirst.umbrella.data.local.standard.Standard
import org.secfirst.umbrella.ui.base.presenter.BasePresenter
import org.secfirst.umbrella.ui.standard.interactor.StandardBaseInteractor
import org.secfirst.umbrella.ui.standard.view.StandardBaseView

interface StandardBasePresenter<V : StandardBaseView, I : StandardBaseInteractor> : BasePresenter<V, I> {
    fun onViewPrepared()

    fun onValidateInsertStandard(standard: Standard)

    fun getData()
}

