package org.secfirst.umbrella.data.database.content

import org.secfirst.umbrella.data.Root
import javax.inject.Inject

class ContentRepository @Inject constructor(private val contentDao: ContentDao) : ContentRepo {

    override fun loadAllLesson(): Lesson = contentDao.getAllContent()

    override fun insertAllLessons(root: Root) = contentDao.insert(root)


}