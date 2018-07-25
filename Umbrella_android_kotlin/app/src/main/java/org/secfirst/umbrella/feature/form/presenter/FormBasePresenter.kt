package org.secfirst.umbrella.feature.form.presenter

import org.secfirst.umbrella.data.Value
import org.secfirst.umbrella.feature.base.presenter.BasePresenter
import org.secfirst.umbrella.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.feature.form.view.FormView

interface FormBasePresenter<V : FormView, I : FormBaseInteractor> : BasePresenter<V, I> {

    fun submitLoadModelForms()

    fun submitLoadActiveForms()

    fun submitInsert(formData: Value)


}