package org.secfirst.whitelabel.data.database.content

import org.secfirst.whitelabel.data.Root
import javax.inject.Inject

class ContentRepository @Inject constructor(private val contentDao: ContentDao) : ContentRepo {


    override fun loadLessons(): Lesson = contentDao.getContents()

    override fun insertAllLessons(root: Root) = contentDao.insertAllLessons(root)

}