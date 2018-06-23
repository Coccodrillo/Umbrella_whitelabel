package org.secfirst.umbrella.ui.content.interactor

import org.secfirst.content.serialize.ElementViewer
import org.secfirst.content.storage.TentStorageRepo
import org.secfirst.umbrella.data.database.content.ContentRepo
import org.secfirst.umbrella.data.database.content.Lesson
import org.secfirst.umbrella.data.network.ApiHelper
import org.secfirst.umbrella.ui.base.interactor.BaseInteractorImp
import javax.inject.Inject

class ContentInteractorImp @Inject constructor(apiHelper: ApiHelper,
                                               private val tentStorageRepo: TentStorageRepo,
                                               private val contentRepo: ContentRepo,
                                               private val elementViewer: ElementViewer)
    : BaseInteractorImp(apiHelper), ContentBaseInteractor {
    override fun serializeRootToLesson(): Lesson {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val root = elementViewer.init()
        val lesson = Lesson()
        lesson.categories.forEach { it.category }
    }

    override fun fetchData() = tentStorageRepo.fetch()

    override fun persistLesson(lesson: Lesson) = contentRepo.insertAllLessons(lesson)

}