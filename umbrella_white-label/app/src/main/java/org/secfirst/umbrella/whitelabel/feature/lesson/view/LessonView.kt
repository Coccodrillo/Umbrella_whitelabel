package org.secfirst.umbrella.whitelabel.feature.lesson.view

import org.secfirst.umbrella.whitelabel.data.database.content.Category
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseView

interface LessonView : BaseView {

    fun showAllLesson(categories: List<Category>)
}