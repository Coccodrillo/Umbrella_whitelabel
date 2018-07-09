package org.secfirst.umbrella.feature.form.view

import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.feature.base.view.BaseView

interface FormBaseView : BaseView {

    fun showForms(forms: List<Form>)
}