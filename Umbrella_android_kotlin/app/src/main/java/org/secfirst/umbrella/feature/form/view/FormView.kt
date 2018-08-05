package org.secfirst.umbrella.feature.form.view

import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.feature.base.view.BaseView

interface FormView : BaseView {
    fun showModelForms(modelForms: List<Form>) {}
    fun showActiveForms(activeForms: List<Form>) {}
}