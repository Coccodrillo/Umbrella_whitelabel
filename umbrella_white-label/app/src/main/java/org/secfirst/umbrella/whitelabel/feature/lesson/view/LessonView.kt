package org.secfirst.umbrella.whitelabel.feature.lesson.view

import org.secfirst.umbrella.whitelabel.data.Difficult
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseView
import org.secfirst.umbrella.whitelabel.feature.lesson.view.adapter.ItemSection

interface LessonView : BaseView {

    fun showAllLesson(itemSections: List<ItemSection>){}

    fun showSelectDifficult(difficulties: List<Difficult>){}
}