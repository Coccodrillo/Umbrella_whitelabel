package org.secfirst.umbrella.whitelabel.feature.form.view

import org.secfirst.umbrella.whitelabel.data.Form
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseView

interface FormView : BaseView {
    fun showModelAndActiveForms(modelForms: List<Form>, activeForms: List<Form>) {}
}