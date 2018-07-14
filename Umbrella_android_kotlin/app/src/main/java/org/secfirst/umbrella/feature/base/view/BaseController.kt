package org.secfirst.umbrella.feature.base.view

import com.bluelinelabs.conductor.Controller
import org.secfirst.umbrella.feature.form.DaggerFormComponent
import org.secfirst.umbrella.feature.form.FormComponent

abstract class BaseController : Controller() {

    init {
        inject()
    }

    private fun inject() = onInject()

    protected abstract fun onInject()
}
