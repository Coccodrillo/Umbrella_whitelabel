package org.secfirst.umbrella.data.database.content

import io.reactivex.Single
import javax.inject.Inject

class ContentRepository @Inject constructor(private val contentDao: ContentDao) : ContentRepo {

    override fun getAllLessons(): Single<Lesson> = contentDao.getAllContent()

    override fun insertAllLessons(lesson: Lesson) = contentDao.insert(lesson)


}