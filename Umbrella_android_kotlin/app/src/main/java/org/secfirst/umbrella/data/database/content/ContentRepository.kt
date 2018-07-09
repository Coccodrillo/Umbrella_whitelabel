package org.secfirst.umbrella.data.database.content

import io.reactivex.Single
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Root
import javax.inject.Inject

class ContentRepository @Inject constructor(private val contentDao: ContentDao) : ContentRepo {

    override fun getForms(): Single<List<Form>> = contentDao.getForms()

    override fun loadLessons(): Lesson = contentDao.getContents()

    override fun insertAllLessons(root: Root) = contentDao.insertAllLessons(root)

}