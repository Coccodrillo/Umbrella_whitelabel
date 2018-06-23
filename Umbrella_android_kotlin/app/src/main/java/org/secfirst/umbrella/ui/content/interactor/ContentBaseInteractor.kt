package org.secfirst.umbrella.ui.content.interactor

import io.reactivex.Single
import org.eclipse.jgit.api.Git
import org.secfirst.umbrella.data.database.content.Lesson
import org.secfirst.umbrella.ui.base.interactor.BaseInteractor

interface ContentBaseInteractor : BaseInteractor {

    fun fetchData(): Single<Git>

    fun persistLesson(lesson: Lesson)

    fun serializeRootToLesson(): Lesson
}