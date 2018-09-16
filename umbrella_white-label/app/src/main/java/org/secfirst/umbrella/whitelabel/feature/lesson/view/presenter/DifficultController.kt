package org.secfirst.umbrella.whitelabel.feature.lesson.view.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseController

class DifficultController : BaseController() {

    override fun onInject() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.difficult_view, container, false)
    }

    override fun getEnableBackAction() = true

    override fun getTitleToolbar() = ""
}