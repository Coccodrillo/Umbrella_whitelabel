package org.secfirst.umbrella.feature.content.presenter

import org.secfirst.umbrella.data.Lesson
import org.secfirst.umbrella.feature.base.presenter.BasePresenter
import org.secfirst.umbrella.feature.content.interactor.ContentBaseInteractor
import org.secfirst.umbrella.feature.content.view.ContentBaseView

interface ContentBasePresenter<V : ContentBaseView, I : ContentBaseInteractor> : BasePresenter<V, I> {

    fun manageContent()

    fun prepareLoadContent(lesson: Lesson)
}