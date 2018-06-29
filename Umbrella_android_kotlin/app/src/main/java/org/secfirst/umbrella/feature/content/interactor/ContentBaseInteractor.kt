package org.secfirst.umbrella.feature.content.interactor

import io.reactivex.Single
import org.eclipse.jgit.api.Git
import org.secfirst.umbrella.data.Root
import org.secfirst.umbrella.data.database.content.Lesson
import org.secfirst.umbrella.feature.base.interactor.BaseInteractor

interface ContentBaseInteractor : BaseInteractor {

    fun fetchData(): Single<Git>

    fun persist(root: Root)

    fun initParser(): Root

    fun getAllLesson(): Lesson

}