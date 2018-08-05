package org.secfirst.whitelabel.feature.content.presenter

import org.secfirst.whitelabel.feature.base.presenter.BasePresenter
import org.secfirst.whitelabel.feature.content.interactor.ContentBaseInteractor
import org.secfirst.whitelabel.feature.content.view.ContentBaseView

interface ContentBasePresenter<V : ContentBaseView, I : ContentBaseInteractor> : BasePresenter<V, I> {

    fun manageContent()

    fun validateLoadAllLesson()
}