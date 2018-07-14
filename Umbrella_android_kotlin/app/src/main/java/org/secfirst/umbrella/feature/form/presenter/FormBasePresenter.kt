package org.secfirst.umbrella.feature.form.presenter

import org.secfirst.umbrella.feature.base.presenter.BasePresenter
import org.secfirst.umbrella.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.feature.form.view.FormBaseView

interface FormBasePresenter<V : FormBaseView, I : FormBaseInteractor> : BasePresenter<V, I> {
    fun loadForms()
}