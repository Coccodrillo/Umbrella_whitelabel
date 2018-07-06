package org.secfirst.umbrella.feature.content.view

import org.secfirst.umbrella.data.database.content.Lesson
import org.secfirst.umbrella.feature.base.view.BaseView

interface ContentBaseView : BaseView {

    fun downloadContent(lesson: Lesson)
    fun onErrorContentNotFound()
}