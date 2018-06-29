package org.secfirst.umbrella.data.database.content

import org.secfirst.umbrella.data.Lesson
import javax.inject.Inject

class ContentRepository @Inject constructor(private val contentDao: ContentDao) : ContentRepo {

    override fun loadAllLesson(): New = contentDao.getAllContent()

    override fun insertAllLessons(lesson: Lesson) = contentDao.insert(lesson)


}