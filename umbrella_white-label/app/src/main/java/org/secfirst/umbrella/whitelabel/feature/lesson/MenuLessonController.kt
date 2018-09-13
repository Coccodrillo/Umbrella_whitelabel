package org.secfirst.umbrella.whitelabel.feature.lesson

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.lesson_view.*
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.data.database.content.Category
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseController

class MenuLessonController : BaseController() {

    private val headerClick: (Category) -> Unit = this::onHeaderClicked
    private val lessonClick: (Category) -> Unit = this::onLessonClicked

    override fun onInject() {

    }

    fun onHeaderClicked(category: Category) {

    }

    fun onLessonClicked(category: Category) {

    }

    override fun onAttach(view: View) {
        lessonMenu?.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = MenuLessonAdapter(headerClick, lessonClick)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.lesson_view, container, false)
    }

    override fun getEnableBackAction() = false

    override fun getTitleToolbar() = ""

}