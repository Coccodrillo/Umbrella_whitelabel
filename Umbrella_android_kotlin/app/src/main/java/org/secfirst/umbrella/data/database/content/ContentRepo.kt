package org.secfirst.umbrella.data.database.content

import io.reactivex.Single
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Root


interface ContentRepo {

    fun insertAllLessons(root: Root)

    fun loadLessons(): Lesson

    fun getForms(): Single<List<Form>>

}