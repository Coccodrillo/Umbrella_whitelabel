package org.secfirst.umbrella.feature.content.interactor

import org.secfirst.umbrella.data.Lesson
import org.secfirst.umbrella.data.database.content.ContentRepo
import org.secfirst.umbrella.data.network.ApiHelper
import org.secfirst.umbrella.data.storage.TentStorageRepo
import org.secfirst.umbrella.feature.base.interactor.BaseInteractorImp
import org.secfirst.umbrella.serialize.ElementLoader
import org.secfirst.umbrella.serialize.ElementSerializer
import javax.inject.Inject

class ContentInteractorImp @Inject constructor(apiHelper: ApiHelper,
                                               private val tentStorageRepo: TentStorageRepo,
                                               private val tentRepo: TentStorageRepo,
                                               private val contentRepo: ContentRepo,
                                               private val elementSerializer: ElementSerializer,
                                               private val elementLoader: ElementLoader)

    : BaseInteractorImp(apiHelper), ContentBaseInteractor {

    override fun fetchData() = tentStorageRepo.fetch()


    override fun initParser(): Lesson {
        val deserializeObj = elementSerializer.serialize(tentRepo.getElementsFile())
        return elementLoader.load(deserializeObj, tentRepo.getLoadersFile())
    }

    override fun persist(lesson: Lesson) = contentRepo.insertAllLessons(lesson)

    override fun getAllLesson() = contentRepo.loadAllLesson()


}