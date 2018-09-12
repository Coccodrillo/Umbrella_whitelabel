package org.secfirst.umbrella.whitelabel.feature.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseController

class MenuLessonController : BaseController() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.lesson_menu_view, container, false)
    }

    override fun onInject() {}

    override fun getEnableBackAction() = false

    override fun getTitleToolbar() = ""

}