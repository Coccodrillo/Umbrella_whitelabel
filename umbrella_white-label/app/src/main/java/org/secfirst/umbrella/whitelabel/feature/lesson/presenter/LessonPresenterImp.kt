package org.secfirst.umbrella.whitelabel.feature.lesson.presenter

import org.secfirst.umbrella.whitelabel.feature.base.presenter.BasePresenterImp
import org.secfirst.umbrella.whitelabel.feature.lesson.interactor.LessonBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.lesson.view.LessonView
import org.secfirst.umbrella.whitelabel.misc.launchSilent
import javax.inject.Inject

class LessonPresenterImp<V : LessonView, I : LessonBaseInteractor> @Inject constructor(
        interactor: I) : BasePresenterImp<V, I>(
        interactor = interactor), LessonBasePresenter<V, I> {

    override fun submitLoadAllLesson() {
        launchSilent {
            interactor?.let {
                val categories = it.fetchLesson()
                getView()?.showAllLesson(categories)
            }
        }
    }
}