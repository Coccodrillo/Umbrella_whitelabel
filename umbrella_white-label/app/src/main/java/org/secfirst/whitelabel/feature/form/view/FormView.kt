package org.secfirst.whitelabel.feature.form.view

import org.secfirst.whitelabel.data.Form
import org.secfirst.whitelabel.feature.base.view.BaseView

interface FormView : BaseView {
    fun showModelForms(modelForms: List<Form>) {}
    fun showActiveForms(activeForms: List<Form>) {}
}