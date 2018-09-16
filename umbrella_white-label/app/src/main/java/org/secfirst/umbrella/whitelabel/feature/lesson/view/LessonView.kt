package org.secfirst.umbrella.whitelabel.feature.lesson.view

import org.secfirst.umbrella.whitelabel.feature.base.view.BaseView
import org.secfirst.umbrella.whitelabel.feature.lesson.view.adapter.LessonMenuAdapter

interface LessonView : BaseView {

    fun showAllLesson(itemSections: List<LessonMenuAdapter.ItemSection>)
}