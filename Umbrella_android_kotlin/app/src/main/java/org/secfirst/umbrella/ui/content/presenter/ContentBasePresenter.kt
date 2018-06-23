package org.secfirst.umbrella.ui.content.presenter

import org.secfirst.umbrella.ui.base.presenter.BasePresenter
import org.secfirst.umbrella.ui.content.ContentBaseView
import org.secfirst.umbrella.ui.content.interactor.ContentBaseInteractor

interface ContentBasePresenter<V : ContentBaseView, I : ContentBaseInteractor> : BasePresenter<V, I> {

    fun manageContent()

    fun getLesson()
}