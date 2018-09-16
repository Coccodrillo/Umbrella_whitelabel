package org.secfirst.umbrella.whitelabel.feature.lesson.presenter

import org.secfirst.umbrella.whitelabel.feature.base.presenter.BasePresenter
import org.secfirst.umbrella.whitelabel.feature.lesson.interactor.LessonBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.lesson.view.LessonView
import org.secfirst.umbrella.whitelabel.feature.lesson.view.adapter.ItemGroup


interface LessonBasePresenter<V : LessonView, I : LessonBaseInteractor> : BasePresenter<V, I> {

    fun submitLoadAllLesson()

    fun submitLessonSelect(lessonSelected: ItemGroup)
}